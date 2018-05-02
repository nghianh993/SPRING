<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import='java.*'%>
<script src="<c:url value="/static/custom/js/organization/organization.js"/>"></script>
<style>
	.ad-form-fn-func {
	    font-size: 16px;
	    padding: 5px;
	}
</style>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li class="active"><i class="ace-icon fa fa-home home-icon"></i>
			<a href="<c:url value='/admin/home' />">Trang chủ </a>
		</li>
		<li>
			Quản lý sơ đồ tổ chức
		</li>
	</ul>
	<!-- /.breadcrumb -->
</div>

<div class="page-content">
	<div class="row">
		<div class="col-xs-12">
			<div class="widget-box widget-color-dark">
				<div class="widget-header">
					<h4 class="widget-title lighter smaller">Sơ đồ tổ chức hệ thống</h4>
					<div class="widget-toolbar">
						<a href="#" data-action="collapse">
							<i class="ace-icon fa fa-chevron-up"></i>
						</a>
						<a href="#" data-action="fullscreen" class="orange2">
							<i class="ace-icon fa fa-expand"></i>
						</a>
					</div>
				</div>
	
				<div class="widget-body">
					<div class="widget-main padding-8">
						<div class="ad-form-fn">
							<button class="btn btn-sm btn-info btnaddnew"><i class="fa fa-file-text-o"></i> Thêm mới</button>
						</div>
						<div id="tree"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="OrgModal" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><i class="fa fa-check-square-o"></i> Thêm mới tổ chức</h4>
            </div>
             
            <form class="form-horizontal" id="frmForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Tên tổ chức</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="txtname" name="txtname" placeholder="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Thuộc</label>
                        <div class="col-sm-9">
                            <select class="form-control slgroup">
                            	<c:forEach items="${lstData }" var="item">
                            		<option value="${item.id }">${item.description }</option>
                            		<c:set var="node" value="${item}" scope="request"/>
    								<jsp:include page="../partialView/organization_select.jsp"></jsp:include>
                            	</c:forEach>
                            </select>
                        </div>
                    </div>                    
                </div>
            </form>
            <div class="modal-footer">
                <button type="submit" class="btn btn-sm btn-success btnModalSubmit"><i class="fa fa-check-square-o"></i> Thêm mới</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="fa fa-times"></i> Đóng</button>
            </div>
        </div>
    </div>
</div>

