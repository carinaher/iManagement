<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp"/>

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
								<td><a href="delete?id=${student.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- /.table-responsive -->
		</div>
	</div>
</div>
<jsp:include page="includes/templateEnd.jsp"/>