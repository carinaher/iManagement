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
			<h3 class="blank1">Student Table</h3>
			<div class="xs tabls">
				<div class="bs-example4" data-example-id="contextual-table">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<p>
								<a href="fillStudents">
									<button type="button" class="btn btn-primary">
										<span aria-hidden="true"></span>Fill List
									</button>
								</a>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="addStudent">
										<button type="button" class="btn btn-primary">
											<span aria-hidden="true"></span>Add Student
										</button>
									</a>
								</sec:authorize>
							</p>
						</div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Username</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>GitHub Username</th>
								<th>Year</th>
								<th>Group</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${students}" var="student">
								<tr>
								
									<th scope="row">${student.id}</th>
									<td>${student.userName}</td>
									<td>${student.firstName}</td>
									<td>${student.lastName}</td>
									<td>${student.githubUser}</td>
									<td>${student.year}</td>
									<td>${student.groupId}</td>
									<td><sec:authorize access="hasRole('ROLE_ADMIN')">
											<a href="editStudent?id=${student.id}"><button
													type="button" class="btn btn-s btn-warning">Edit</button></a>
										</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
											<a href="deleteStudent?id=${student.id}"><button
													type="button" class="btn btn-s btn-danger">
													Delete</button></a>
										</sec:authorize></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- /.table-responsive -->
			</div>
		</div>
	</div>
</div>

<jsp:include page="includes/templateEnd.jsp" />