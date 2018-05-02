$(document).ready(function() {
	var root = "/cms";
	$('#slper, #sluser').chosen({
		disable_search: false,
		width: '100%', 
		placeholder_text_multiple : 'Chọn quyền', 
		include_group_label_in_selected:true,
		search_contains: true,
		enable_split_word_search: true,
		no_results_text: "Không có dữ liệu!"
	});
	$.validator.setDefaults({ ignore: ":hidden:not(.chosen-select)" });  
	var totalpage = parseInt($('#datatable').attr("data-total"));
    var totalrecord = parseInt($('#datatable').attr("data-totalrecord"));
    var pageSize = 2;
    
	var active_class = 'active';
	$('#listtable > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
		var th_checked = this.checked;//checkbox inside "TH" table header
		
		$(this).closest('table').find('tbody > tr').each(function(){
			var row = this;
			if(th_checked) {
				$(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
				$(".btneditall, .btndeleteall").removeClass("disabled");
			}
			else {
				$(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				$(".btneditall, .btndeleteall").addClass("disabled");
			}
		});
	});
	
	$('#listtable').on('click', 'td input[type=checkbox]' , function(){
		var $row = $(this).closest('tr');
		if($row.is('.detail-row ')) return;
		if(this.checked) {
			$row.addClass(active_class);
			$(".btneditall, .btndeleteall").removeClass("disabled");
		}
		else {
			 $row.removeClass(active_class);
			 $(".btneditall, .btndeleteall").addClass("disabled");
		}
	});	

    if (totalpage > 1) {
        var obj = $('#pagination').twbsPagination({
            totalPages: totalpage,
            visiblePages: 3,
            first: 'Trang đầu',
            prev: 'Trước',
            next: 'Tiếp',
            last: 'Trang cuối',
            onPageClick: function (event, page) {
                var curentpage = parseInt($('#datatable').attr("data-page"));
                if (curentpage != page) {
                    if (typeof $(".ddlpage").val() != "undefined") {
                        pageSize = parseInt($(".ddlpage").val());
                    }
                    var data = {
                		pageIndex: page,
                		pageSize: pageSize
                    };
                    fis_vnp_js.fis_vnp().loadDataAll("/cms/api/admin/group/loaddata", "listtable", data);
                }
            }
        });
    }

    $(document).on("click", ".btnedit", function() {
        var _this = $(this);
        var id = _this.attr("data-id");
		$.LoadingOverlay("show");
        var data = {
            id: id
        }
        $.post(root + "/api/admin/group/detail", data, function(resp){
            $.LoadingOverlay("hide");
            if(resp.result){
				$("#txtrolename").val(resp.resultData.name);
				$('#sluser').val(resp.resultData.lstuser).trigger("chosen:updated");
				$("#slper").val(resp.resultData.lstPermision).trigger("chosen:updated");
				$("#GroupModal .modal-title").html('<i class="fa fa-check-square-o"></i> Cập nhật nhóm quyền')
				$(".btnModal").attr("data-id", id);
				$(".btnModal").html('<i class="fa fa-check-square-o"></i> Cập nhật');
				$(".btnModal").removeClass("btnadd");
				$(".btnModal").addClass("btnupdate");
				$("#GroupModal").modal("show");
            }else{
				fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm lấy dữ liệu. Vui lòng thao tác lại!!!", "error");
			}
        });   
	});
	
	$(document).on("click", ".btnaddnew", function() {
		$(".btnModal").addClass("btnadd");
		$(".btnModal").removeClass("btnupdate");
		$(".btnModal").html('<i class="fa fa-check-square-o"></i> Thêm mới');
		$("#GroupModal .modal-title").html('<i class="fa fa-check-square-o"></i> Thêm mới nhóm quyền');
		$("#txtrolename").val("");
		$('#sluser').val(0).trigger("chosen:updated");
		$("#slper").val(0).trigger("chosen:updated");
		$("#GroupModal").modal("show");
	});

	$(document).on("click", ".btndelete", function(){
		var _this = $(this);
		var id = _this.attr("data-id");
		swal({
            title: "Thông báo",
            text: "Bạn có chắc muốn xóa nhóm quyền này không?",
            type: "warning",
            dangerMode: true,
            showCancelButton: true,
            confirmButtonText: "Đồng ý",
            cancelButtonText: "Đóng",
            closeOnConfirm: false
        }).then((isConfirm) => { 
            if (isConfirm) {
        		fis_vnp_js.fis_vnp().postDataAndReloadPage(root + "/api/admin/group/delete", {id:id}, "Xóa nhóm quyền thành công", "Hệ thống gặp sự cố khi xóa dữ liệu. Vui lòng thao tác lại!!!");
            }
        });
	});

    $(document).on("click", ".btnModal", function() {
		$("#frmForm").submit();
    });
    
    $("#frmForm").validate({
		rules: {
			txtrolename : {
				required: true
            },
            slper: {
                required: true
            },
			sluser: {
				required: true
			}
		},
		messages: {
			txtlink: {
				required: "Tên nhóm quyền không được để trống"
            },
            slper: {
                required: "Chọn quyền hệ thống"
            },
			sluser: {
				required: "Chọn tài khoản vào group"
			}
		},
		submitHandler : function() {
			var data  = {				
				name: $.trim($("#txtrolename").val()),
                lstuser: $("#sluser").val().toString(),
                lstPermision: $("#slper").val().toString()
			}
			$.LoadingOverlay("show");
			if($(".btnModal").hasClass("btnadd")){
				$.post(root + "/api/admin/group/add", data, function(resp) {
					$.LoadingOverlay("hide");
					if(resp.result){
						if(resp.code == 1){
							fis_vnp_js.fis_vnp().showmessage("Thêm mới nhóm quyền thành công", "success");
							$("#GroupModal").modal("hide");
							location.reload();
						}else{
							fis_vnp_js.fis_vnp().showmessage("Không thêm được nhóm quyền!!!", "error");
						}
					}else{
						fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm mới dữ liệu. Vui lòng thao tác lại!!!", "error");
					}
				});
			}else{
				if($(".btnModal").hasClass("btnupdate")){
					data.id = parseInt($(".btnModal").attr("data-id"));
					$.post(root + "/api/admin/group/edit", data, function(resp) {
						$.LoadingOverlay("hide");
						if(resp.result){
							if(resp.code == 1){
								fis_vnp_js.fis_vnp().showmessage("Cập nhật nhóm quyền thành công", "success");
								$("#GroupModal").modal("hide");
								location.reload();
							}else{
								fis_vnp_js.fis_vnp().showmessage("Không cập nhật được thông tin nhóm quyền!!!", "error");
							}
						}else{
							fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm cập nhật dữ liệu. Vui lòng thao tác lại!!!", "error");
						}
					});
				}
			}
		}
    });
});