<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<h3 class="blank1">ToDo List</h3>
			<div class="bs-example4" data-example-id="contextual-table">
				<div class="row">
					<div class="col-md-15 col-md-offset-0">
						<p>
							<a href="fillTasks?returnUrl=task">

								<button type="button" class="btn btn-primary">Fill List</button>

							</a> <a href="addTask?returnUrl=task">
								<button type="button" class="btn btn-primary">Add Task</button>
							</a>
						<br>
						</p>
					</div>
				</div>
				<div class="xs tabls">

					<table class="table table-todo_list">
						<thead>
							<tr>

								<th>Title</th>
								<th>Description</th>
								<th>Status</th>
								<th>Start</th>
								<th>End</th>
								<th>Place</th>
								<th>UserName</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${tasks}" var="task">
								
								<c:choose>
								<c:when test="${task.status}">
								<tr class="done">
								</c:when>
								<c:otherwise>
								<tr class="not-done">
								</c:otherwise>
								</c:choose>
								
								
									<td>${task.title}</td>
									<td>${task.description}</td>
									
									<td>
										<c:choose>
											<c:when test="${task.status}">
												done
											</c:when>
											<c:otherwise>
												not done
											</c:otherwise>
										</c:choose>
									</td>
									
									
									<td><fmt:formatDate value="${task.start}"
											pattern="dd.MM.yyyy" /></td>
									<td><fmt:formatDate value="${task.end}"
											pattern="dd.MM.yyyy" /></td>
									<td>${task.place}</td>
									<td>${task.userName}</td>

									<td><a href="editTask?id=${task.id}&returnUrl=task"><button
												type="button" class="btn btn-s btn-warning">Edit</button></a> <a
										href="deleteTask?id=${task.id}&returnUrl=task"><button type="button"
												class="btn btn-s btn-danger">Delete</button></a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<!-- /.table-responsive -->
		</div>
	</div>
</div>
</div>

<jsp:include page="includes/templateEnd.jsp" />