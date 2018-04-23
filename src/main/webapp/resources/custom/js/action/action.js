$(document).ready(function() {
    var root = "/cms";
    $('#slmethod').chosen({
		allow_single_deselect:true, 
		width: '100%', 
		placeholder_text_multiple : 'Phương thức'
	});
    $('#slper').chosen({
		allow_single_deselect:true, 
		disable_search: false,
		width: '100%', 
		placeholder_text_multiple : 'Chọn quyền', 
		include_group_label_in_selected:true,
		search_contains: true,
		enable_split_word_search: true,
		no_results_text: "Không có dữ liệu!"
	});
    $.validator.setDefaults({ ignore: ":hidden:not(.chosen-select)" });  
	$.validator.addMethod("valueNotEquals", function(value, element, arg){
		  return arg !== value;
	 }, "Giá trị không hợp lệ");
	
	$(document).on('hidden.bs.modal', '.modal', function() {
		$('.modal:visible').length && $(document.body).addClass('modal-open');
    });	
    
    $(document).on("click", ".btnaddaction", function() {
		var _this = $(this);
		var id = _this.attr("data-id");
		loadGoup();
		$("#actionModal .modal-title").html('<i class="fa fa-check-square-o"></i> Thêm mới chức năng')
		$(".btnModal").addClass("btnaddac");
		$(".btnModal").removeClass("btnupdateac");
		$("#actionModal").modal("show");
		setTimeout(function() {$(".slgrac").val(id);}, 500);
    });
    
    $(document).on("click", ".btnaddgroup", function(e) {
		e.preventDefault();
		loadGoup();
		$("#actionModal").modal("hide");
		$("#goupModal").modal("show");
    });
    
    $(document).on("click", ".btnaddgr", function() {
		$("#frmFormAddGroup").submit();
	});
	
	$(document).on("click", ".btnModal", function() {
		$("#frmFormAction").submit();
    });
    
    $("#frmFormAction").validate({
		rules: {
			txtlink : {
				required: true
            },
            slmethod: {
                required: true
            },
			slgrac: {
				valueNotEquals: "0"
			}
		},
		messages: {
			txtlink: {
				required: "Đường dẫn không được để trống"
            },
            slmethod: {
                required: "Chọn phương thức chức năng"
            },
			slgrac: {
				valueNotEquals: "Chọn một nhóm chức năng"
			}
		},
		submitHandler : function() {
			var data  = {
				link: $.trim($("#txtlink").val()),
				islock: $(".chklock").prop("checked"),
                parentId: $(".slgrac").val(),
                methods: $("#slmethod").val().toString(),
                permission: $("#slper").val().toString()
			}
			$.LoadingOverlay("show");
			if($(".btnModal").hasClass("btnaddac")){
				$.post(root + "/api/admin/action/add", data, function(resp) {
					$.LoadingOverlay("hide");
					if(resp.result){
						if(resp.code == 1){
							fis_vnp_js.fis_vnp().showmessage("Thêm mới chức năng thành công", "success");
							$("#permissionModal").modal("hide");
							location.reload();
						}else{
							fis_vnp_js.fis_vnp().showmessage("Không thêm được chức năng vào nhóm chức năng!!!", "error");
						}
					}else{
						fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm mới dữ liệu. Vui lòng thao tác lại!!!", "error");
					}
				});
			}else{
				if($(".btnModal").hasClass("btnupdateac")){
					data.id = parseInt($(".btnModal").attr("data-id"));
					$.post(root + "/api/admin/action/edit", data, function(resp) {
						$.LoadingOverlay("hide");
						if(resp.result){
							if(resp.code == 1){
								fis_vnp_js.fis_vnp().showmessage("Cập nhật chức năng thành công", "success");
								$("#permissionModal").modal("hide");
								location.reload();
							}else{
								fis_vnp_js.fis_vnp().showmessage("Không cập nhật được thông tin chức năng!!!", "error");
							}
						}else{
							fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm cập nhật dữ liệu. Vui lòng thao tác lại!!!", "error");
						}
					});
				}
			}
		}
    });
    
    $("#frmFormAddGroup").validate({
		rules: {
			txtnamegroup: {
				required: true
			}
		},
		messages: {
			txtnamegroup: {
				required: "Tên nhóm chức năng không được để trống"
			}
		},
		submitHandler : function() {
			var data = {
				name: $.trim($("#txtnamegroup").val()),
				parentId : $(".slgroupadd").val()
			};
			$.LoadingOverlay("show");
			$.post(root + "/api/admin/action/addgroup", data, function(resp) {
				$.LoadingOverlay("hide");
				if(resp.result){
					fis_vnp_js.fis_vnp().showmessage("Thêm mới nhóm chức năng thành công", "success");
					$("#goupModal").modal("hide");
					loadGoup();
					$("#actionModal").modal("show");
				}else{
					fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm mới dữ liệu. Vui lòng thao tác lại!!!", "error");
				}
			});
		}
    });

    $(document).on("click", ".btneditac", function() {
        var _this = $(this);
        var id = _this.attr("data-id");		
		loadGoup();
		$.LoadingOverlay("show");
        var data = {
            id: id
        }
        $.post(root + "/api/admin/action/detail", data, function(resp){
            $.LoadingOverlay("hide");
            if(resp.result){
				$("#txtlink").val(resp.resultData.link);
				$(".slgrac").val(resp.resultData.parentId);				
				$("#slmethod").val(resp.resultData.methods).trigger("chosen:updated");
				$("#slper").val(resp.resultData.permission).trigger("chosen:updated");
				$("#actionModal .modal-title").html('<i class="fa fa-check-square-o"></i> Cập nhật chức năng');
				$(".btnModal").attr("data-id", id);
				$(".btnModal").text("Cập nhật");
				$(".btnModal").removeClass("btnaddac");
				$(".btnModal").addClass("btnupdateac");
				$("#actionModal").modal("show");
            }else{
				fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm lấy dữ liệu. Vui lòng thao tác lại!!!", "error");
			}
        });       
    });
    

    function loadGoup() {
		$.LoadingOverlay("show");
		$.post(root + "/api/admin/action/loadgroup", function(resp) {
			$.LoadingOverlay("hide");
			$(".slgroup").empty();
			$(".slgroup").html($.trim(resp));
		});
	}
});