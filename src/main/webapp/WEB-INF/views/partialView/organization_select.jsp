<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "custom" uri="/WEB-INF/taglib/string-utils.tld"%>

<c:forEach var="node" items="${node.children}">
    <option value="${node.id }"> ${custom:leftPad("", node.lever * 3, "-")} ${ node.description }</option>
    <c:set var="node" value="${node}" scope="request"/>
    <jsp:include page="organization_select.jsp"></jsp:include>
</c:forEach>