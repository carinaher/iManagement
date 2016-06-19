<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<jsp:include page="includes/templateStart.jsp" />

<div class="container" role="main">
	<!--  Error message ----------------------------------------------------------- -->
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>
	<!--  Error message ----------------------------------------------------------- -->

	<!--  Warning message ----------------------------------------------------------- -->
	<c:if test="${not empty warningMessage}">
		<div class="alert alert-warning" role="warning">
			${warningMessage}</div>
	</c:if>
	<!--  Warning message ----------------------------------------------------------- -->

	<!--   message ----------------------------------------------------------- -->
	<c:if test="${not empty message}">
		<div class="alert alert-success" role="warning">${message}</div>
	</c:if>
	<!--   message ----------------------------------------------------------- -->


	<div id="page-wrapper">
		<div class="graphs">
			<h3 class="blank1">Forum</h3>
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<p margin-left="auto" margin-right="auto">

						<a href="fillForumEntrys">

							<button type="button" class="btn btn-primary">Fill Forum</button></a>
							
							<a href="addForumEntry">

								<button type="button" class="btn btn-primary">New Entry</button></a>
					</p>
				<br>
				</div>
			</div>
			<c:forEach items="${forumEntrys}" var="entry">
				<div class="xs tabls">
					<div class="panel-body1">
						<table class="table">
							<thead>
								<tr>
									<th>Topic</th>
									<th>Text</th>
																		<th>Action</th>
									
								</tr>
							</thead>
							<tbody>

								<tr>
									<td>${entry.topic}</td>
									<td>${entry.text}</td>
									<td><a href="editForumEntry?id=${entry.id}"><button
												type="button" class="btn btn-s btn-warning">Edit</button></a> <a
										href="deleteForumEntry?id=${entry.id}"><button type="button"
												class="btn btn-s btn-danger">Delete</button></a></td>
									

								</tr>

							</tbody>
						</table>

					</div>

					<!-- /.table-responsive -->
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<jsp:include page="includes/templateEnd.jsp" />