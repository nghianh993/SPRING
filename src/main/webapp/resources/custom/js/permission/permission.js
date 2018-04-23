$(document).ready(function() {
	var root = "/cms";
	 $('#slmethod').chosen({
		allow_single_deselect:true, 
		width: '100%', 
		placeholder_text_multiple : 'Phương thức'
	});
	$.validator.setDefaults({ ignore: ":hidden:not(.chosen-select)" });
	$.validator.addMethod("valueNotEquals", function(value, element, arg){
		  return arg !== value;
	 }, "Giá trị không hợp lệ");
	
	$(document).on('hidden.bs.modal', '.modal', function() {
		$('.modal:visible').length && $(document.body).addClass('modal-open');
	});	
	
	$(document).on("click", ".btnaddpermission", function() {
		var _this = $(this);
		var id = _this.attr("data-id");
		loadGoup();
		$("#permissionModal .modal-title").html('<i class="fa fa-check-square-o"></i> Thêm mới chức năng')
		$(".btnModal").addClass("btnaddper");
		$(".btnModal").removeClass("btnupdateper");

		$("#txtname").val("");
		$("#txtcode").val("");
		$("#txtlink").val("");
		$(".slgrper").val(0);
		$("#slmethod").val(0).trigger("chosen:updated");

		$("#permissionModal").modal("show");
		setTimeout(function() {$(".slgrper").val(id);}, 100);
	});

	$(document).on("click", ".btnaddgroup", function(e) {
		e.preventDefault();
		loadGoup();
		$("#permissionModal").modal("hide");
		$("#goupModal").modal("show");
	});
	
	$(document).on("click", ".btnaddgr", function() {
		$("#frmFormAddGroup").submit();
	});
	
	$(document).on("click", ".btnaddper, .btnupdateper", function() {
		$("#frmFormPermission").submit();
	});
	
	$("#frmFormPermission").validate({
		rules: {
			txtcode: {
				required: true
			},
			txtname : {
				required: true
			},
			slgrper: {
				valueNotEquals: "0"
			},
			slmethod: {
                required: true
            }
		},
		messages: {
			txtcode: {
				required: "Mã quyền không được để trống"
			},
			txtname : {
				required: "Tên không được để trống"
			},
			slgrper: {
				valueNotEquals: "Chọn một nhóm quyền"
			},
            slmethod: {	
                required: "Chọn phương thức chức năng"
            }
		},
		submitHandler : function() {
			var data  = {
				code: $.trim($("#txtcode").val()),
				description: $.trim($("#txtname").val()),
				islock: $(".chklock").prop("checked"),
				parentId: $(".slgrper").val(),
				link: $("#txtlink").val(),
				methods: $("#slmethod").val().toString()
			}
			$.LoadingOverlay("show");
			if($(".btnModal").hasClass("btnaddper")){
				$.post(root + "/api/admin/permission/add", data, function(resp) {
					$.LoadingOverlay("hide");
					if(resp.result){
						if(resp.code == 1){
							fis_vnp_js.fis_vnp().showmessage("Thêm mới quyền thành công", "success");
							$("#permissionModal").modal("hide");
							location.reload();
						}else{
							fis_vnp_js.fis_vnp().showmessage("Không thêm được quyền vào nhóm quyền!!!", "error");
						}
					}else{
						fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm mới dữ liệu. Vui lòng thao tác lại!!!", "error");
					}
				});
			} else {
				if($(".btnModal").hasClass("btnupdateper")){
					data.id = parseInt($(".btnModal").attr("data-id"));
					$.post(root + "/api/admin/permission/edit", data, function(resp) {
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
	})
	
	$("#frmFormAddGroup").validate({
		rules: {
			txtnamegroup: {
				required: true
			}
		},
		messages: {
			txtnamegroup: {
				required: "Tên nhóm quyền không được để trống"
			}
		},
		submitHandler : function() {
			var data = {
				name: $.trim($("#txtnamegroup").val()),
				parentId : $(".slgroupadd").val()
			};
			$.LoadingOverlay("show");
			$.post(root + "/api/admin/permission/addgroup", data, function(resp) {
				$.LoadingOverlay("hide");
				if(resp.result){
					fis_vnp_js.fis_vnp().showmessage("Thêm mới nhóm quyền thành công", "success");
					$("#goupModal").modal("hide");
					loadGoup();
					$("#permissionModal").modal("show");
				}else{
					fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm mới dữ liệu. Vui lòng thao tác lại!!!", "error");
				}
			});
		}
	});

	$(document).on("click", ".btneditper", function() {
		var _this = $(this);
		var id = _this.attr("data-id");

		loadGoup();
		$.LoadingOverlay("show");
        var data = {
            id: id
        }
        $.post(root + "/api/admin/permission/detail", data, function(resp){
            $.LoadingOverlay("hide");
            if(resp.result){
				$("#txtname").val(resp.resultData.description);
				$("#txtcode").val(resp.resultData.code);
				$("#txtlink").val(resp.resultData.link);
				$(".slgrper").val(resp.resultData.parentId);
				$("#slmethod").val(resp.resultData.methods).trigger("chosen:updated");
				
				$("#permissionModal .modal-title").html('<i class="fa fa-check-square-o"></i> Cập nhật chức năng');
				$(".btnModal").attr("data-id", id);
				$(".btnModal").text("Cập nhật");
				$(".btnModal").removeClass("btnaddper");
				$(".btnModal").addClass("btnupdateper");
				$("#permissionModal").modal("show");
            }else{
				fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm lấy dữ liệu. Vui lòng thao tác lại!!!", "error");
			}
        });
	});
	
	$(document).on("click", ".btndelPer", function() {
		var _this = $(this);
		var id = _this.attr("data-id");
		var data = {
			id : id
		};
		swal({
            title: "Thông báo",
            text: "Bạn có chắc muốn khóa quyền này không?",
            type: "warning",
            dangerMode: true,
            showCancelButton: true,
            confirmButtonText: "Đồng ý",
            cancelButtonText: "Đóng",
            closeOnConfirm: false
        }).then((isConfirm) => { 
            if (isConfirm) {
        		fis_vnp_js.fis_vnp().postDataAndReloadPage(root + "/api/admin/permission/delete", data, "Xóa quyền thành công", "Hệ thống gặp sự cố khi xóa dữ liệu. Vui lòng thao tác lại!!!");
            }
        });
	});
	
	$(document).on("click", ".btnlockper", function() {
		var _this = $(this);
		var id = _this.attr("data-id");
		var data = {
			id : id
		};
		swal({
            title: "Thông báo",
            text: "Bạn có chắc muốn khóa quyền này không?",
            dangerMode: true,
            showCancelButton: true,
            confirmButtonText: "Đồng ý",
            cancelButtonText: "Đóng",
            closeOnConfirm: false
        }).then((isConfirm) => { 
            if (isConfirm) {
				_this.removeClass("btnlockper");
				_this.addClass("btnunlockper");
				fis_vnp_js.fis_vnp().postDataAndReloadPage(root + "/api/admin/permission/lock", data, "Khóa quyền thành công", "Hệ thống gặp sự cố khi thao tác dữ liệu. Vui lòng thao tác lại!!!");
    		}
        });
	});
	
	$(document).on("click", ".btnunlockper", function() {
		var _this = $(this);
		var id = _this.attr("data-id");
		var data = {
			id : id
		};
		swal({
            title: "Thông báo",
            text: "Bạn có chắc muốn mở khóa quyền này không?",
            type: "warning",
            dangerMode: true,
            showCancelButton: true,
            confirmButtonText: "Đồng ý",
            cancelButtonText: "Đóng",
            closeOnConfirm: false
        }).then((isConfirm) => { 
            if (isConfirm) {
				_this.removeClass("btnunlockper");
				_this.addClass("btnlockper");
				fis_vnp_js.fis_vnp().postDataAndReloadPage(root + "/api/admin/permission/unlock", data, "Mở khóa quyền thành công", "Hệ thống gặp sự cố khi thao tác dữ liệu. Vui lòng thao tác lại!!!");
            }
        });
	});

	function loadGoup() {
		$.LoadingOverlay("show");
		$.post(root + "/api/admin/permission/loadgroup", function(resp) {
			$.LoadingOverlay("hide");
			$(".slgroup").empty();
			$(".slgroup").html($.trim(resp));
		});
	}
});