<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar-buttons navbar-header pull-right" role="navigation">
	<ul class="nav ace-nav">
		<li class="grey dropdown-modal"><a data-toggle="dropdown"
			class="dropdown-toggle" href="#"> <i class="ace-icon fa fa-tasks"></i>
				<span class="badge badge-grey">4</span>
		</a>

			<ul
				class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
				<li class="dropdown-header"><i class="ace-icon fa fa-check"></i>
					4 Tasks to complete</li>

				<li class="dropdown-content">
					
				</li>

				<li class="dropdown-footer"><a href="#"> See tasks with
						details <i class="ace-icon fa fa-arrow-right"></i>
				</a></li>
			</ul></li>

		<li class="purple dropdown-modal"><a data-toggle="dropdown"
			class="dropdown-toggle" href="#"> <i
				class="ace-icon fa fa-bell icon-animated-bell"></i> <span
				class="badge badge-important">8</span>
		</a>

			<ul
				class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
				<li class="dropdown-header"><i
					class="ace-icon fa fa-exclamation-triangle"></i> 8 Notifications</li>

				<li class="dropdown-footer"><a href="#"> See all
						notifications <i class="ace-icon fa fa-arrow-right"></i>
				</a></li>
			</ul></li>

		<li class="green dropdown-modal"><a data-toggle="dropdown"
			class="dropdown-toggle" href="#"> <i
				class="ace-icon fa fa-envelope icon-animated-vertical"></i> <span
				class="badge badge-success">5</span>
		</a>

			<ul
				class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
				<li class="dropdown-header"><i
					class="ace-icon fa fa-envelope-o"></i> 5 Messages</li>

				<li class="dropdown-content">
					
				</li>

				<li class="dropdown-footer"><a href="inbox.html"> See all
						messages <i class="ace-icon fa fa-arrow-right"></i>
				</a></li>
			</ul></li>

		<li class="light-blue dropdown-modal"><a data-toggle="dropdown"
			href="#" class="dropdown-toggle"> <img class="nav-user-photo"
				src="<c:url value="/static/images/avatars/user.jpg"/>" alt="Jason's Photo" /> <span
				class="user-info"> <small>Xin chào,</small> Admin
			</span> <i class="ace-icon fa fa-caret-down"></i>
		</a>

			<ul
				class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
				<li><a href="#"> <i class="ace-icon fa fa-cog"></i> Cài đặt
				</a></li>

				<li><a href="profile.html"> <i class="ace-icon fa fa-user"></i>
						Thông tin
				</a></li>

				<li class="divider"></li>

				<li><a href="<c:url value="/account/logout" />"> <i
						class="ace-icon fa fa-power-off"></i> Đăng xuất
				</a></li>
			</ul></li>
	</ul>
</div>
