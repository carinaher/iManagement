<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%--  set user name, role and profile picture  ------------------------------- --%>
<sec:authentication var="user" property="principal" />
<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
<sec:authorize access="hasRole('ROLE_STUDENT')" var="isStudent" />
<%-- // user name --%>
<c:choose>
	<c:when test="${not empty user}">
		<c:set var="username">${user.username}</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="username">Guest</c:set>
	</c:otherwise>
</c:choose>
<%-- // role --%>
<c:choose>
	<c:when test="${isAdmin}">
		<c:set var="role">Administrator</c:set>
	</c:when>
	<c:when test="${isStudent}">
		<c:set var="role">Student</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="role">User</c:set>
	</c:otherwise>
</c:choose>
<%-- // profile picture --%>
<c:choose>
	<c:when test="${isAdmin}">
		<c:set var="profileImage">resources/images/admin.png</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="profileImage">resources/images/user1.png</c:set>
	</c:otherwise>
</c:choose>
<%-- / set user name, role and profile picture  ------------------------------ --%>



<!--  set user name, role and profile picture  -------------------------------- -->
<c:choose>
	<c:when test="${not empty student}">
		<c:set var="legend">Edit Student ${student.id}</c:set>
		<c:set var="formAction">editStudent</c:set>
		<c:set var="readonly">readonly</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="legend">New Student</c:set>
		<c:set var="formAction">addStudent</c:set>
		<c:set var="readonly"></c:set>
	</c:otherwise>
</c:choose>
<!--/  set user name, role and profile picture  ------------------------------- -->
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<c:if test="${not empty pageTitle}">
	<title>${pageTitle} | iManagement</title>
</c:if>
<c:if test="${empty pageTitle}">
	<title>iManagement</title>
</c:if>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Easy Admin Panel Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<!-- Custom CSS -->
<link href="resources/css/style.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="resources/css/font-awesome.css" rel="stylesheet">
<!-- jQuery -->
<!-- lined-icons -->
<link rel="stylesheet" href="resources/css/icon-font.min.css"
	type='text/css' />
<!-- //lined-icons -->
<!-- chart -->
<script src="resources/js/Chart.js"></script>
<!-- //chart -->
<!--animate-->
<link href="resources/css/animate.css" rel="stylesheet" type="text/css"
	media="all">
<script src="resources/js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->
<!----webfonts--->
<link
	href='//fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic'
	rel='stylesheet' type='text/css'>
<!---//webfonts--->
<!-- Meters graphs -->
<script src="resources/js/jquery-1.10.2.min.js"></script>
<!-- Placed js at the end of the document so the pages load faster -->

</head>

<body class="sticky-header left-side-collapsed" onload="initMap()">
	<section>
		<!-- left side start-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<h1>
					<a href="/iManagement"><span>iMA</span>NAGEMENT</a>
				</h1>
			</div>
			<div class="logo-icon text-center">
				<a href="/iManagement"><i class="lnr lnr-home"></i> </a>
			</div>

			<!--logo and iconic logo end-->
			<div class="left-side-inner">

				<!--sidebar nav start-->
				<ul class="nav nav-pills nav-stacked custom-nav">

					<li><a href="student"><i class="lnr lnr-users"></i> <span>Students</span></a></li>
					<li><a href="task"><i class="lnr lnr-list"></i> <span>Tasks</span></a></li>
					<li><a href="forum"><i class="lnr lnr-bubble"></i> <span>Forum</span></a></li>
				</ul>
				<!--sidebar nav end-->
			</div>
		</div>
		<!-- left side end-->

		<!-- main content start-->
		<div class="main-content main-content4">
			<!-- header-starts -->
			<div class="header-section">

				<!--toggle button start-->
				<a class="toggle-btn  menu-collapsed"><i class="fa fa-bars"></i></a>
				<!--toggle button end-->

				<!--notification menu start -->
				<div class="menu-right">
					<div class="user-panel-top">
						<%--			
						<div class="profile_details_left">
							<ul class="nofitications-dropdown">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" aria-expanded="false"><i
										class="fa fa-envelope"></i><span class="badge">3</span></a>

									<ul class="dropdown-menu">
										<li>
											<div class="notification_header">
												<h3>You have 3 new messages</h3>
											</div>
										</li>
										<li><a href="#">
												<div class="user_img">
													<img src="images/1.png" alt="">
												</div>
												<div class="notification_desc">
													<p>Lorem ipsum dolor sit amet</p>
													<p>
														<span>1 hour ago</span>
													</p>
												</div>
												<div class="clearfix"></div>
										</a></li>
										<li class="odd"><a href="#">
												<div class="user_img">
													<img src="images/1.png" alt="">
												</div>
												<div class="notification_desc">
													<p>Lorem ipsum dolor sit amet</p>
													<p>
														<span>1 hour ago</span>
													</p>
												</div>
												<div class="clearfix"></div>
										</a></li>
										<li><a href="#">
												<div class="user_img">
													<img src="images/1.png" alt="">
												</div>
												<div class="notification_desc">
													<p>Lorem ipsum dolor sit amet</p>
													<p>
														<span>1 hour ago</span>
													</p>
												</div>
												<div class="clearfix"></div>
										</a></li>
										<li>
											<div class="notification_bottom">
												<a href="#">See all messages</a>
											</div>
										</li>
									</ul></li>
								<li class="login_box" id="loginContainer">
									<div class="search-box">
										<div id="sb-search" class="sb-search">
											<form>
												<input class="sb-search-input"
													placeholder="Enter your search term..." type="search"
													id="search"> <input class="sb-search-submit"
													type="submit" value=""> <span
													class="sb-icon-search"> </span>
											</form>
										</div>
									</div> <!-- search-scripts --> <script src="js/classie.js"></script>
									<script src="js/uisearch.js"></script> <script>
										new UISearch(document
												.getElementById('sb-search'));
									</script> <!-- //search-scripts -->
								</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" aria-expanded="false"><i
										class="fa fa-bell"></i><span class="badge blue">3</span></a>
									<ul class="dropdown-menu">
										<li>
											<div class="notification_header">
												<h3>You have 3 new notification</h3>
											</div>
										</li>
										<li><a href="#">
												<div class="user_img">
													<img src="images/1.png" alt="">
												</div>
												<div class="notification_desc">
													<p>Lorem ipsum dolor sit amet</p>
													<p>
														<span>1 hour ago</span>
													</p>
												</div>
												<div class="clearfix"></div>
										</a></li>
										<li class="odd"><a href="#">
												<div class="user_img">
													<img src="images/1.png" alt="">
												</div>
												<div class="notification_desc">
													<p>Lorem ipsum dolor sit amet</p>
													<p>
														<span>1 hour ago</span>
													</p>
												</div>
												<div class="clearfix"></div>
										</a></li>
										<li><a href="#">
												<div class="user_img">
													<img src="images/1.png" alt="">
												</div>
												<div class="notification_desc">
													<p>Lorem ipsum dolor sit amet</p>
													<p>
														<span>1 hour ago</span>
													</p>
												</div>
												<div class="clearfix"></div>
										</a></li>
										<li>
											<div class="notification_bottom">
												<a href="#">See all notification</a>
											</div>
										</li>
									</ul></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" aria-expanded="false"><i
										class="fa fa-tasks"></i><span class="badge blue1">22</span></a>
									<ul class="dropdown-menu">
										<li>
											<div class="notification_header">
												<h3>You have 8 pending task</h3>
											</div>
										</li>
										<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Database update</span><span
														class="percentage">40%</span>
													<div class="clearfix"></div>
												</div>
												<div class="progress progress-striped active">
													<div class="bar yellow" style="width: 40%;"></div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Dashboard done</span><span
														class="percentage">90%</span>
													<div class="clearfix"></div>
												</div>

												<div class="progress progress-striped active">
													<div class="bar green" style="width: 90%;"></div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Mobile App</span><span
														class="percentage">33%</span>
													<div class="clearfix"></div>
												</div>
												<div class="progress progress-striped active">
													<div class="bar red" style="width: 33%;"></div>
												</div>
										</a></li>
										<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Issues fixed</span><span
														class="percentage">80%</span>
													<div class="clearfix"></div>
												</div>
												<div class="progress progress-striped active">
													<div class="bar  blue" style="width: 80%;"></div>
												</div>
										</a></li>
										<li>
											<div class="notification_bottom">
												<a href="#">See all pending task</a>
											</div>
										</li>
									</ul></li>
								<div class="clearfix"></div>
							</ul>
							
						</div>
						--%>
						<div class="profile_details">
							<ul>
								<li class="dropdown profile_details_drop"><a href="#"
									class="dropdown-toggle" data-toggle="dropdown"
									aria-expanded="false">
										<div class="profile_img" id="profile_img">
											<span
												style="background: url(${profileImage}) no-repeat center">
											</span>
											<div class="user-name">
												<p>${username}<span>${role}</span>
												</p>
											</div>
											<i class="lnr lnr-chevron-down"></i> <i
												class="lnr lnr-chevron-up"></i>
											<div class="clearfix"></div>
										</div>
								</a>
									<ul class="dropdown-menu drp-mnu">
										<li><a href="my_profile"><i class="fa fa-cog"></i>
												Settings</a></li>
										<%-- <li><a href="my_profile"><i class="fa fa-user"></i>Profile</a></li> --%>
										<li>
											<form class="form-horizontal" method="post" action="logout">
												<fieldset>
													<button type="submit" class="btn btn-link">
														<i class="fa fa-sign-out"></i> Logout
													</button>
													<%-- <a href="logout"><i class="fa fa-sign-out"></i> Logout</a> --%>
												</fieldset>
												<input type="hidden" name="${_csrf.parameterName }"
													value="${_csrf.token }" />
											</form>
										</li>
									</ul></li>
								<div class="clearfix"></div>
							</ul>
						</div>

						<div class="clearfix"></div>
					</div>
				</div>
				<!--notification menu end -->


			</div>
			<!-- //header-ends -->