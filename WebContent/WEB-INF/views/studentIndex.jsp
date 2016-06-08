<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp"/>

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
			<div class="alert alert-success" role="warning">
				${message}</div>
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
								<button type="button" class="btn btn-success">Fill List</button>
							</a>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href="addStudent">
								<button type="button" class="btn btn-success">Add Student</button>
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
								<td>${student.year}</td>
								<td>${student.groupId}</td>
								<td>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a href="deleteStudent?id=${student.id}">Delete</a>
								<a href="editStudent?id=${student.id}">Edit</a>
								</sec:authorize>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- /.table-responsive -->
		</div>
	</div>
</div>

<c:url value="/logout" var="logoutUrl" />
		<form action="${ logoutUrl}" method="post">
			<input type="hidden" name="${_csrf.parameterName }"
				value="${ _csrf.token}" /> <input class="btn btn-xs btn-danger"
				type="submit" value="Logout" />
		</form>



<jsp:include page="includes/templateEnd.jsp"/>