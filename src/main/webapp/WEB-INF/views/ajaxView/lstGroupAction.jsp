<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<option value="0">Root</option>
<c:forEach var="entry" items="${lstData}">
	<c:if test="${entry.value != null && entry.value.size() > 0}">
		<c:forEach var="item" items="${ entry.value }">
			<option value="${item.id }">${item.name }</option>
		</c:forEach>
	</c:if>
</c:forEach>