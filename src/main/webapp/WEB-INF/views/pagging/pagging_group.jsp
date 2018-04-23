<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${lstRole}" var="item">
	<tr>
		<td class="center">
			<label class="pos-rel"> 
				<input type="checkbox" data-id="${item.id}" class="ace" /> <span class="lbl"></span>
		</label>
	</td>
	<td>${item.rolename}</td>
	<td style="text-align: center;">
			<div class="hidden-sm hidden-xs action-buttons">
				<a class="green btnedit" data-id="${item.id}" href="#" title="Cập nhật thông tin">
					<i class="ace-icon fa fa-pencil bigger-130"></i>
				</a>

				<a class="red btndelete" data-id="${item.id}" href="#" title="Xóa">
					<i class="ace-icon fa fa-trash-o bigger-130"></i>
				</a>
			</div>
		</td>
	</tr>
</c:forEach>
<input type="hidden" id="datatable" data-total="${totalPage }" data-totalrecord="${total }" data-page="${currentpage }" />