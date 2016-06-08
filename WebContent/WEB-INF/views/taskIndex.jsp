<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="includes/templateStart.jsp"/>


<div id="page-wrapper">
	<div class="graphs">
		<h3 class="blank1">Tasks Table</h3>
		<div class="xs tabls">
			<div class="bs-example4" data-example-id="contextual-table">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<p>
							<a href="fillTasks">
								<button type="button" class="btn btn-success">Fill List</button>
							</a>
							<a href="addTask">
								<button type="button" class="btn btn-success">Add Task</button>
							</a>
						</p>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Task Name</th>
							<th>Description</th>
							<th>Status</th>
							<th>Due Date</th>
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
								<td><fmt:formatDate value="${task.dueDate}" pattern="dd.MM.yyyy"/></td>
								<td><a href="deleteTask?id=${task.id}">Delete</a></td>
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