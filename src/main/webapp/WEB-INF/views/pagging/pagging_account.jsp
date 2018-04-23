<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${lstUser}" var="user">
	<tr>
		<td class="center">
			<label class="pos-rel"> 
				<input type="checkbox" data-id="${user.id}" class="ace" /> <span class="lbl"></span>
			</label>
		</td>
		<td>${user.email}</td>
		<td>${user.fullname}</td>
		<td>${user.address }</td>
		<td>${user.phone }</td>
		<td>
			<c:if test="${user.islock}">
				<span class="label label-sm label-danger">Khóa</span>
		    </c:if>
		    <c:if test="${!user.islock}">
				<span class="label label-sm label-success">Không khóa</span>
		    </c:if>
		</td>
		<td>${user.datecreate }</td>
		<td style="text-align: center;">
			<div class="hidden-sm hidden-xs action-buttons">
				<a class="blue" href="#">
					<i class="ace-icon fa fa-search-plus bigger-130"></i>
				</a>
	
				<a class="green" href="#">
					<i class="ace-icon fa fa-pencil bigger-130"></i>
				</a>
	
				<a class="red" href="#">
					<i class="ace-icon fa fa-trash-o bigger-130"></i>
				</a>
			</div>
		</td>
	</tr>
</c:forEach>
<input type="hidden" id="datatable" data-total="${totalPage }" data-totalrecord="${total }" data-page="${currentpage }" />