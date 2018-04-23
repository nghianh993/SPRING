<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="vi">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title><tiles:getAsString name="title" /></title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="SHORTCUT ICON" href="<c:url value="/static/favicon.ico"/>" type="image/x-icon">
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<c:url value="/static/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/static/font-awesome/4.5.0/css/font-awesome.min.css"/>" />

		<!-- text fonts -->
		<link rel="stylesheet" href="<c:url value="/static/css/fonts.googleapis.com.css"/>" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<c:url value="/static/css/ace.min.css"/>" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="<c:url value="/static/css/ace-skins.min.css"/>" />
		<link rel="stylesheet" href="<c:url value="/static/css/chosen.min.css"/>" />
		
		<link rel="stylesheet" href="<c:url value="/static/custom/lib/gritter/css/jquery.gritter.css"/>" />
		<link rel="stylesheet" href="<c:url value="/static/css/custom.css"/>" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<c:url value="/static/css/ace-ie.min.css"/>" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="<c:url value="/static/js/ace-extra.min.js"/>"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<c:url value="/static/js/html5shiv.min.js"/>"></script>
		<script src="<c:url value="/static/js/respond.min.js"/>"></script>
		<![endif]-->
		
		
		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="<c:url value="/static/js/jquery-2.1.4.min.js"/>"></script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script src="<c:url value="/static/js/jquery-1.11.3.min.js"/>"></script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="<c:url value="/static/js/jquery-ui.custom.min.js"/>"></script>
	
		<!-- ace scripts -->
		<script src="<c:url value="/static/js/ace-elements.min.js"/>"></script>
		<script src="<c:url value="/static/js/ace.min.js"/>"></script>
		<script src="<c:url value="/static/js/jquery.validate.min.js"/>"></script>
		<script src="<c:url value="/static/custom/lib/gritter/js/jquery.gritter.min.js"/>"></script>
		<script src="<c:url value="/static/custom/lib/jquery.twbsPagination.min.js"/>"></script>
		<script src="<c:url value="/static/custom/lib/sweetalert.min.js"/>"></script>
		<script src="<c:url value="/static/js/chosen.jquery.min.js"/>"></script>
		<script src="<c:url value="/static/custom/lib/loadingoverlay.js"/>"></script>
		<script src="<c:url value="/static/custom/js/common.js"/>"></script>
	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>

				<div class="navbar-header pull-left">
					<a href="index.html" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							Ace Admin
						</small>
					</a>
				</div>

				<tiles:insertAttribute name="header" />
			</div><!-- /.navbar-container -->
		</div>

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<tiles:insertAttribute name="menu" />
			
			<div class="main-content">
				<div class="main-content-inner">
					
					<tiles:insertAttribute name="body" />
					
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
				<tiles:insertAttribute name="footer" />
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

	</body>
</html>

