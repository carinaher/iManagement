<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp" />
<link
	href="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"
	rel="stylesheet">

<!--  add or edit?  ----------------------------------------------------------- -->
<c:choose>
	<c:when test="${not empty task}">
		<c:set var="legend">Edit Task ${task.id}</c:set>
		<c:set var="formAction">editTask</c:set>
		<c:set var="readonly">readonly</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="legend">New Task</c:set>
		<c:set var="formAction">addTask</c:set>
		<c:set var="readonly"></c:set>
	</c:otherwise>
</c:choose>
<!--  add or edit?  ----------------------------------------------------------- -->


<div id="page-wrapper">
	<div class="graphs">
		<h3 class="blank1">${legend}</h3>
		<div class="xs tabls">
			<div class="bs-example4" data-example-id="contextual-table">

				<table class="table">
					<form class="form-horizontal" method="post" action="${formAction}">
						<fieldset>


							<! ----------------  Id ---------------- -->
							<div class="form-group">
								<label for="inputId" class="col-md-2 control-label">Id</label>
								<div class="col-md-10">
									<input class="form-control" id="inputId" type="text" name="id"
										${readonly} value="<c:out value="${task.id}"/>">
								</div>
							</div>


							<! ----------------  TaskName ---------------- -->
							<div class="form-group">
								<label for="inputTaskName" class="col-md-2 control-label">Task
									Name</label>
								<div class="col-md-10">
									<input class="form-control" id="inputTaskName" type="text"
										name="taskName" value="<c:out value="${task.taskName}"/>">
								</div>
							</div>

							<! ----------------  Description ---------------- -->
							<div class="form-group">
								<label for="inputDescription" class="col-md-2 control-label">Description</label>
								<div class="col-md-10">
									<input class="form-control" id="inputDescription" type="text"
										name="description"
										value="<c:out value="${task.description}"/>">
								</div>
							</div>

							<! ----------------  Status ---------------- -->
							<div class="form-group">
								<label for="inputStatus" class="col-md-2 control-label">Status</label>
								<div class="col-md-10">
									<input class="form-control" id="inputStatus" type="text"
										name="status" value="<c:out value="${task.status}"/>">
								</div>
							</div>

							<! ----------------  dueDate---------------- -->
							<div class="form-group">
								<label for="inputDueDate" class="col-md-2 control-label">Due
									Date</label>
								<div class="col-md-10">
									<input class="form_datetime" id="inputDueDate"
										placeholder="Due Date" type="text" readonly name="dueDate"
										value="<fmt:formatDate value="${task.dueDate}" pattern="dd.MM.yyyy"/>">
								</div>
							</div>


							<! ----------------  buttons ---------------- -->
							<div class="form-group">
								<div class="col-md-10 col-md-offset-2">
									<button type="submit" class="btn btn-primary">Submit</button>
									<a href="task">
										<button type="button" class="btn btn-default">Cancel</button>
									</a>
								</div>
							</div>
						</fieldset>
					</form>
				</table>
			</div>

			<!-- /.table-responsive -->
		</div>
	</div>
</div>
<!-- JS for Datetime picker -->

<script type="text/javascript"
	src="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>

<script>
	$(function() {

		$(".form_datetime").datetimepicker({
			format : "dd.mm.yyyy",
			autoclose : true,
			todayBtn : true,
			pickerPosition : "bottom-left",
			minView : 2
		});

	});
</script>


<jsp:include page="includes/templateEnd.jsp" />
