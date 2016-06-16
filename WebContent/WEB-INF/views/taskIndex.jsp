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
			<h3 class="blank1">Tasks Table</h3>
			<div class="xs tabls">
				<div class="bs-example4" data-example-id="contextual-table">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<p>
								<a href="fillTasks">

									<button type="button" class="btn btn-primary">Fill
										List</button>

								</a> <a href="addTask">
										<button type="button" class="btn btn-primary">Add
											Task</button>
									</a>
							</p>
						</div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th data-sortable="true">ID</th>
								<th data-sortable="true">Task Name</th>
								<th data-sortable="true">Description</th>
								<th data-sortable="true">Status</th>
								<th data-sortable="true">Due Date</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${tasks}" var="task">
								<tr>
									<th scope="row">${task.id}</th>
									<td>${task.taskName}</td>
									<td>${task.description}</td>
									<td>${task.status}</td>
									<td><fmt:formatDate value="${task.dueDate}"
											pattern="dd.MM.yyyy" /></td>
									
											<td>
											<a href="editTask?id=${task.id}"><button
													type="button" class="btn btn-s btn-warning">Edit</button></a>
											<a href="deleteTask?id=${task.id}"><button
													type="button" class="btn btn-s btn-danger">
													Delete</button></a>
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
</div>

<jsp:include page="includes/templateEnd.jsp" />