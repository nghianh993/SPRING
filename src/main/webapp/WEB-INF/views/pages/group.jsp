<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="<c:url value="/static/custom/js/group/group.js"/>"></script>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li class="active"><i class="ace-icon fa fa-home home-icon"></i>
			<a href="<c:url value='/admin/home' />">Trang chủ </a>
		</li>
		<li>
			Quản lý nhóm quyền
		</li>
	</ul>
	<!-- /.breadcrumb -->
</div>

<div class="page-content">
	<div class="row">
	<div class="col-xs-12 lo-paging-0">
            <div class="col-md-2"></div>
            <div class="input-group col-xs-12 col-md-8 lo-paging-0">
                <input class="form-control txtsearchkey" placeholder="Tìm kiếm theo Email hoặc SĐT" type="text"  />
                <span class="input-group-btn">
                    <button type="button" class="btn btn-inverse btn-white btnsearch" style="background-color: #4267b2 !important; color: white !important;" >
                        <span class="ace-icon fa fa-search icon-on-right bigger-110 searchicon"></span>
                        Tìm
                    </button>
                </span>
            </div>
        </div>
	<div class="col-xs-12">
		<div class="space"></div>
        <div class="hd-fuc">
            <div class="col-md-9 col-xs-12 btn-area-action">
                <button class="btn btn-sm btn-primary btn-blue btnaddnew" data-toggle="modal" data-target="#userModal">
                    <i class="fa fa-check-square bigger-125"></i>
                    Thêm mới
                </button>
                <!-- <button class="btn btn-sm btn-default btneditall disabled">
                    <i class="fa fa-edit bigger-125"></i>
                    Sửa
                </button>

                <button class="btn btn-sm btn-danger btndeleteall disabled">
                    <i class="fa fa-trash-o bigger-125"></i>
                    Xóa
                </button> -->
            </div>
            <div class="col-md-3 col-xs-12 viewpage">
                <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">
                    Tổng số bản ghi : <span class="totalrecord">${total}</span>
                </div>
            </div>
        </div>
	
	</div>
	
		<div class="col-xs-12">
			<table id="listtable" class="table table-bordered table-hover">
				<thead>
					<tr>
						<th class="center" style="width: 80px;">
							<label class="pos-rel"> 
								<input type="checkbox" class="ace" /> <span class="lbl"></span>
							</label>
						</th>
						<th>Tên</th>
						<th style="width: 150px"></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${lstRole}" var="item">
					<tr>
						<td class="center" style="width: 80px;">
							<label class="pos-rel"> 
								<input type="checkbox" data-id="${item.id}" class="ace" /> <span class="lbl"></span>
							</label>
						</td>
						<td>${item.rolename}</td>
						<td style="text-align: center;">
							<div class="hidden-sm hidden-xs action-buttons">
								<a class="green btnedit" data-id="${item.id}" href="javascript: ;" title="Cập nhật thông tin">
									<i class="ace-icon fa fa-pencil bigger-130"></i>
								</a>

								<a class="red btndelete" data-id="${item.id}" href="javascript: ;" title="Xóa">
									<i class="ace-icon fa fa-trash-o bigger-130"></i>
								</a>
							</div>
						</td>
					</tr>
					<input type="hidden" id="datatable" data-total="${totalPage }" data-totalrecord="${total }" data-page="${currentpage }" />
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		<div class="pagecus">
		<c:if test="${totalPage > 1}">
            <div class="ht-mor-pagination page-home lo-paging">
                <div class="col-xs-12 col-md-4 dv_choose_page">
                    <label>
                        Hiển thị
                        <select class="ddlpage">
                            <option value="2">2</option>
                            <option value="30">30</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                            <option value="150">150</option>
                            <option value="200">200</option>
                        </select>
                        đơn hàng
                    </label>
                </div>
                <div class="col-xs-12 col-md-8 lo-paging-0 dv_page" style="text-align: right;">
                    <div class="dataTables_paginate homepagging ">
                        <div class="pagination" id="pagination"></div>
                    </div>
                </div>
            </div>
         </c:if>
    </div>
	</div>
	<!-- /.span -->
	
	<!-- Modal -->
  <div class="modal fade" id="GroupModal" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><i class="fa fa-check-square-o"></i> Thêm mới nhóm quyền</h4>
        </div>
        <form class="form-horizontal" id="frmForm">
        <div class="modal-body">
             <div class="form-group">
               <label  class="col-sm-3 control-label">Tên nhóm quyền</label>
               <div class="col-sm-9">
                   <input type="text" class="form-control" id="txtrolename" name="txtrolename" placeholder=""/>
               </div>
             </div>
             <div class="form-group">
                  <label class="col-sm-3 control-label">Phân quyền chức năng</label>
                  <div class="col-sm-9">
                      <select class="chosen-select form-control tag-input-style" id="slper" name="slper" multiple="multiple">
                      <c:forEach var="entry" items="${lstDataPer}"> 
							<c:if test="${entry.value != null && entry.value.size() > 0}">
								<c:forEach var="item" items="${ entry.value }">
									<optgroup label="${ item.name}">
									<c:if test="${ item.getPermissions() != null && item.getPermissions().size() > 0 }">
										<c:forEach var="permission" items="${item.getPermissions() }">
											<option value="${permission.id }">${ permission.description } - ${ permission.code }</option>
										</c:forEach>
									</c:if>
									</optgroup>
								</c:forEach>
							</c:if>
						</c:forEach>
                      </select>
                  </div>
       		</div>
       		
       		<div class="form-group">
                  <label class="col-sm-3 control-label">Tài khoản</label>
                  <div class="col-sm-9">
                      <select class="chosen-select form-control tag-input-style" id="sluser" name="sluser" multiple="multiple">
                      	<c:forEach var="user" items="${lstUser}"> 
							<option value="${user.id}">
								${ user.fullname }(${user.email })
							</option>
						</c:forEach>
                      </select>
                  </div>
       		</div>
       	</div>
       	</form>
        <div class="modal-footer">
          <button type="button" class="btn btn-sm btn-success btnModal"><i class="fa fa-check-square-o"></i> Thêm mới</button>
          <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="fa fa-times"></i> Đóng</button>
        </div>
      </div>
    </div>
  </div>
</div>

