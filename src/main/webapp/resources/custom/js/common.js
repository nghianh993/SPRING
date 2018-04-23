/*
* Create 09/04/2018
* NghiaN
*/

var fis_vnp_js = (function () {
    var fis_vnp_js_common = function () { };
    fis_vnp_js_common.prototype.clock = function () {
        var today = new Date();

        var y = "  " + today.getFullYear();
        var mt = " Tháng " + (today.getMonth() + 1);
        var d = ", " + (today.getDate());
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        h = fis_vnp_js.fis_vnp().checkTime(h);
        m = fis_vnp_js.fis_vnp().checkTime(m);
        s = fis_vnp_js.fis_vnp().checkTime(s);

        var clocktime = fn_xnk_js.xnk_js().getday(today.getDay()) + d + mt + y + " - " + h + ":" + m + ":" + s;
        $(".navbar-header .hd-clock").html(clocktime);
        var t = setTimeout(function () {
            fn_xnk_js.xnk_js().clock();
        }, 500);
    };
    fis_vnp_js_common.prototype.checkTime = function (i) {
        if (i < 10) { i = "0" + i };
        return i;
    }
    fis_vnp_js_common.prototype.getday = function (day) {
        if (day == 1) {
            return "Thứ 2"
        }
        if (day == 2) {
            return "Thứ 3"
        }
        if (day == 3) {
            return "Thứ tư"
        }
        if (day == 4) {
            return "Thứ 5"
        }
        if (day == 5) {
            return "Thứ 6"
        }
        if (day == 6) {
            return "Thứ 7"
        }
        if (day == 0) {
            return "Chủ nhật"
        }
    }
    fis_vnp_js_common.prototype.activeTab = function () {
        var link = window.location.href.toLowerCase();;
        $(".tab_order .nav-tabs li").each(function () {
            var url = $(this).children().attr("href").toLowerCase();;
            if (link.indexOf(url) != -1) {
                $(this).addClass("active");
            }
        });
    }
    
    fis_vnp_js_common.prototype.postDataAndReloadPage = function(link, data, messageSS, messageEr){
    	$.LoadingOverlay("show");
    	$.post(link, data, function(resp){
    		$.LoadingOverlay("hide");
			if(resp.result){
				fis_vnp_js.fis_vnp().showmessage(messageSS, "success");
				setTimeout(function() {
					location.reload();
				}, 1500);
			}else{
				fis_vnp_js.fis_vnp().showmessage(messageEr, "error");
			}
    	});
    }
    
    fis_vnp_js_common.prototype.showmessage = function(message, status){
	    $.gritter.add({
	        title: "Thông báo",
	        text: message,
	        class_name: 'gritter-' + status + ' gritter-light'
	    });
    }

    //paging all page
    fis_vnp_js_common.prototype.loadDataAll = function (linkApi, tagAppend, data) {
        $.LoadingOverlay("show");
        $.post(linkApi, data, function (resp) {
            if (resp != null) {
                $("#" + tagAppend + " tbody").html("");
                $("#" + tagAppend + " tbody").html(resp);
                if (typeof $(".page-home").val() != "undefined") {
                    $(".page-home").show();
                } else {
                    var html = '<div class="page-home lo-paging"><div class="col-xs-12 col-md-4"><div class="dataTables_length"><label>Hiển thị <select class="ddlpage"><option value="30">30</option><option value="50">50</option><option value="100">100</option><option value="150">150</option><option value="200">200</option></select> tin</label></div></div><div class="col-xs-12 col-md-8 lo-paging-0"><div class="dataTables_paginate homepagging "><div class="pagination" id="pagination"></div></div></div></div>';
                    $(".pagecus").append(html);
                }
                $.LoadingOverlay("hide");
            }
        });
    }
    fis_vnp_js_common.prototype.loadDataAndPagingAll = function (linkApi, tagAppend, data) {
        $.LoadingOverlay("show");
        $.post(linkApi, data, function (resp) {
            if (resp != null) {
                $("#" + tagAppend + " tbody").html("");
                $("#" + tagAppend + " tbody").html(resp);
                if (resp.TotalPage > 1) {
                    if (typeof $(".page-home").val() != "undefined") {
                        $(".page-home").show();
                    } else {
                        var html = '<div class="page-home lo-paging"><div class="col-xs-12 col-md-4"><div class="dataTables_length"><label>Hiển thị <select class="ddlpage"><option value="30">30</option><option value="50">50</option><option value="100">100</option><option value="150">150</option><option value="200">200</option></select> tin</label></div></div><div class="col-xs-12 col-md-8 lo-paging-0"><div class="dataTables_paginate homepagging "><div class="pagination" id="pagination"></div></div></div></div>';
                        $(".pagecus").append(html);
                    }
                    fn_xnk_js.xnk_js().showPaginationAll(resp.TotalPage, linkApi, tagAppend, data);
                } else {
                    $(".page-home").hide();
                }
                $.LoadingOverlay("hide");
            }
        });
    }
    fis_vnp_js_common.prototype.showPaginationAll = function (pagesCounter, linkApi, tagAppend, data) {
        $('#pagination').remove();
        $('.homepagging').html('<div class="pagination" id="pagination"></div>');
        $('#pagination').twbsPagination({
            totalPages: pagesCounter,
            visiblePages: 3,
            first: 'Trang đầu',
            prev: 'Trước',
            next: 'Tiếp',
            last: 'Trang cuối',
            onPageClick: function (event, page) {
                var curentpage = parseInt($('#datatable').attr("data-page"));
                if (curentpage != page) {
                    $.LoadingOverlay("show");
                    $.post(linkApi, data, function (resp) {
                        if (resp != null) {
                            $("#" + tagAppend + " tbody").html("");
                            $("#" + tagAppend + " tbody").html(resp);
                            if (typeof $(".page-home").val() != "undefined") {
                                $(".page-home").show();
                            } else {
                                var html = '<div class="page-home lo-paging"><div class="col-xs-12 col-md-4"><div class="dataTables_length"><label>Hiển thị <select class="ddlpage"><option value="30">30</option><option value="50">50</option><option value="100">100</option><option value="150">150</option><option value="200">200</option></select> tin</label></div></div><div class="col-xs-12 col-md-8 lo-paging-0"><div class="dataTables_paginate homepagging "><div class="pagination" id="pagination"></div></div></div></div>';
                                $(".pagecus").append(html);
                            }
                            $.LoadingOverlay("hide");
                        }
                    });
                }
            }
        });
    }

    return {
        fis_vnp: function () {
            var fis_vnp = new fis_vnp_js_common();
            return fis_vnp;
        }
    };
})();