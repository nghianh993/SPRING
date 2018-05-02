<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="<c:url value="/static/custom/js/permission/permission.js"/>"></script>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li class="active"><i class="ace-icon fa fa-home home-icon"></i>
			<a href="<c:url value='/admin/home' />">Trang chủ </a>
		</li>
		<li>
			Quản lý quyền hệ thống
		</li>
	</ul>
	<!-- /.breadcrumb -->
</div>

<div class="page-content">
	<div class="col-xs-12">
		<div class="dd" id="nestable">
			
			<c:forEach var="entry" items="${lstData}"> 
				<c:if test="${entry.value != null && entry.value.size() > 0}">
					
					<c:forEach var="item" items="${ entry.value }">
						<ol class="dd-list">
							<li class="dd-item">
								<div class="dd-handle"> ${ item.name}
									<div class="pull-right action-buttons">
										<a class="blue btnaddpermission" href="javascript: ;" data-id="${item.id }" title="Thêm quyền"> 
											<i class="ace-icon fa fa-plus-square bigger-130"></i>
										</a> 
										<a class="blue btneditgoup" href="javascript: ;" title="Sửa" data-id="${item.id }"> 
											<i class="ace-icon fa fa-pencil bigger-130"></i>
										</a> 
										<a class="red btndeletegroup" href="javascript: ;" title="Xóa chức năng" data-id="${item.id }"> 
											<i class="ace-icon fa fa-trash-o bigger-130"></i>
										</a>
									</div>
								</div>
								
								<c:if test="${ item.getPermissions() != null && item.getPermissions().size() > 0 }">
									<ol class="dd-list">
										<c:forEach var="permission" items="${ item.getPermissions() }">
											<li class="dd-item">
													<div class="dd-handle operation">
														 ${ permission.code } - ${ permission.description }
														<div class="pull-right action-buttons">
															<a class="blue btneditper" href="javascript: ;" title="Sửa" data-id="${permission.id }"> 
																<i class="ace-icon fa fa-pencil bigger-130"></i>
															</a> 
															<c:if test="${!permission.islock}">
																<a data-id="${permission.id }" class="red btnlockper" href="javascript: ;" title="Khóa quyền" > 
																	<i class="ace-icon fa fa-unlock-alt bigger-130"></i>
																</a>
															</c:if>
															
															<c:if test="${permission.islock}">
																<a data-id="${permission.id }" class="red btnunlockper" href="javascript: ;" title="Mở khóa quyền" > 
																	<i class="ace-icon fa fa-lock bigger-130"></i>
																</a>
															</c:if>
															
															<a class="red btndelPer" href="javascript: ;" title="Xóa quyền" data-id="${permission.id }"> 
																<i class="ace-icon fa fa-trash-o bigger-130"></i>
															</a>
														</div>
													</div>
												</li>
										</c:forEach>
									</ol>
								</c:if>
							</li>
						</ol>
					</c:forEach>
					
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="permissionModal" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><i class="fa fa-check-square-o"></i> Thêm mới chức năng</h4>
            </div>
            <form class="form-horizontal" id="frmFormPermission">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Mã CODE</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="txtcode" name="txtcode"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Tên chức năng</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="txtname" name="txtname" placeholder="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Đường dẫn</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="txtlink" name="txtlink" placeholder="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Phương thức</label>
                        <div class="col-sm-9">
                            <select class="chosen-select form-control tag-input-style" id="slmethod" name="slmethod" multiple="multiple">
                            	<c:forEach items="${lstmethod }" var="method">
                            		<option value="${method.id}">
                            			${method.name }
                            		</option>
                            	</c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Nhóm chức năng</label>
                        <div class="col-sm-8">
                            <select class="form-control slgroup slgrper" name="slgrper">
                            	<c:forEach var="entry" items="${lstData}"> 
                            		<c:if test="${entry.value != null && entry.value.size() > 0}">
                            			<c:forEach var="item" items="${ entry.value }">
                            				<option value="${item.id }"> ${item.name }</option>
                            			</c:forEach>
                            		</c:if>
                            	</c:forEach>
                            </select>
                        </div>
                        <button class="btn btn-sm btn-success btnaddgroup" title="Thêm mới nhóm quyền">
                        	<i class="fa fa-plus"></i>
                        </button>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Khóa</label>
                        <div class="col-sm-9">
                            <label>
								<input name="chklock" class="ace ace-switch ace-switch-6 chklock" type="checkbox">
								<span class="lbl"></span>
							</label>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="submit" class="btn btn-sm btn-success btnModal"><i class="fa fa-check-square-o"></i> Thêm mới</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="fa fa-times"></i> Đóng</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="goupModal" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><i class="fa fa-check-square-o"></i> Thêm mới nhóm quyền</h4>
            </div>
            <form class="form-horizontal" id="frmFormAddGroup">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Tên quyền</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="txtnamegroup" name="txtnamegroup" placeholder="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Nhóm quyền</label>
                        <div class="col-sm-9">
                            <select class="form-control slgroup slgroupadd">                            	
                            </select>
                        </div>
                    </div>                    
                </div>
            </form>
            <div class="modal-footer">
                <button type="submit" class="btn btn-sm btn-success btnGroupModal"><i class="fa fa-check-square-o"></i> Thêm mới</button>
                <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="fa fa-times"></i> Đóng</button>
            </div>
        </div>
    </div>
</div>
