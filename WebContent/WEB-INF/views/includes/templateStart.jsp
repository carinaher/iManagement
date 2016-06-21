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

<link rel='stylesheet' href='resources/fullcalendar/fullcalendar.css' />
<script src='resources/fullcalendar/jquery.min.js'></script>
<script src='resources/fullcalendar/moment.min.js'></script>
<script src='resources/fullcalendar/fullcalendar.min.js'></script>

<!-- <script src="resources/js/jquery-1.10.2.min.js"></script>
Placed js at the end of the document so the pages load faster -->

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
					<li><a href="calendar"><i class="lnr lnr-calendar-full"></i> <span>Calendar</span></a></li>
					<li><a href="timetable"><i class="lnr lnr-graduation-hat"></i> <span>Timetable</span></a></li>
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
									
						<div class="profile_details_left">
							<ul class="nofitications-dropdown">
									
								<li>
									<div class="search-box">
										<div id="sb-search" class="sb-search">
											<form method="post" action="findStudent">
												<input class="sb-search-input"
													placeholder="Enter your search term..." type="text"
													id="search" name="searchString">
												  
													<input class="sb-search-submit" type="submit" value="Search"> 
													<span class="sb-icon-search"> </span>
													<input type="hidden" name="${_csrf.parameterName }"
									value="${_csrf.token }" />
											</form>
										</div>
									</div> <!-- search-scripts --> <script src="resources/js/classie.js"></script>
									<script src="resources/js/uisearch.js"></script> <script>
										new UISearch(document.getElementById('sb-search'));
									</script> <!-- //search-scripts -->
								</li>
							</ul>
							
						</div>
					
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