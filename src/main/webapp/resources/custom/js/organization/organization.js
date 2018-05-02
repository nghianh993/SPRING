$(document).ready(function(){
    var root = "/cms";
    $.post(root + "/api/admin/organization/loaddata", function(resp) {
        if(resp.result){
            $('#tree').hortree({ data: resp.resultData });
        }
    });

    $(".btnaddnew").click(function(){
        $(".btnModalSubmit").removeClass("btnupdate");
        $(".btnModalSubmit").addClass("btnadd");
        $("#OrgModal .modal-title").html('<i class="fa fa-check-square-o"></i> Thêm mới tổ chức');
        $("#OrgModal").modal("show");
    });

    $(document).on("click", ".btnModalSubmit", function () {
		$("#frmForm").submit();
	});

    $("#frmForm").validate({
		rules: {
			txtname : {
				required: true
            }
		},
		messages: {
			txtname : {
				required: "Tên không được để trống"
            }
		},
		submitHandler : function() {
			var data  = {				
				description: $.trim($("#txtname").val()),
                parentId: $(".slgroup").val()
			}
			$.LoadingOverlay("show");
			if($(".btnModalSubmit").hasClass("btnadd")){
				$.post(root + "/api/admin/organization/add", data, function(resp) {
					$.LoadingOverlay("hide");
					if(resp.result){
                        fis_vnp_js.fis_vnp().showmessage("Thêm mới dữ liệu thành công", "success");
                        $("#OrgModal").modal("hide");
                        location.reload();
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
                            fis_vnp_js.fis_vnp().showmessage("Cập nhật nhóm quyền thành công", "success");
                            $("#GroupModal").modal("hide");
                            location.reload();
						}else{
							fis_vnp_js.fis_vnp().showmessage("Hệ thống gặp sự cố khi thêm cập nhật dữ liệu. Vui lòng thao tác lại!!!", "error");
						}
					});
				}
			}
		}
    });
});