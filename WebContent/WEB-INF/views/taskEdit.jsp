<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<c:set var="formAction">editTask?returnUrl=${returnUrl}</c:set>
		<c:set var="readonly">readonly</c:set>
		<c:set var="username">${task.userName}</c:set>
		<c:if test="${task.status}">
			<c:set var="statusChecked">checked</c:set>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:set var="legend">New Task</c:set>
		<c:set var="formAction">addTask?returnUrl=${returnUrl}</c:set>
		<c:set var="readonly"></c:set>
		<c:set var="username">${userDetails.username}</c:set>
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

							<c:if test="${not empty task}">
								<!-- ----------------  Id ---------------- -->
								<div class="form-group">
									<label for="inputId" class="col-md-2 control-label">Id</label>
									<div class="col-md-10">
										<input class="form-control" id="inputId" type="text" name="id"
											${readonly} value="<c:out value="${task.id}"/>">
									</div>
								</div>
							</c:if>


							<!-- ----------------  Title ---------------- -->
							<div class="form-group">
								<label for="inputTitle" class="col-md-2 control-label">Title</label>
								<div class="col-md-10">
									<input class="form-control" id="inputTitle" type="text"
										name="title" value="<c:out value="${task.title}"/>">
								</div>
							</div>

							<!-- ----------------  Description ---------------- -->
							<div class="form-group">
								<label for="inputDescription" class="col-md-2 control-label">Description</label>
								<div class="col-md-10">
									<input class="form-control" id="inputDescription" type="text"
										name="description"
										value="<c:out value="${task.description}"/>">
								</div>
							</div>
							
							

							<!-- ----------------  Status ---------------- -->
							<div class="form-group">
								<label for="inputStatus" class="col-md-2 control-label">Status</label>
								<div class="col-md-10">								
									<input type="checkbox" ${statusChecked} class="form-control" id="inputStatus"
										name="statusCheckbox" value="a_checkbox_for_status"> Task is done
								</div>
							</div>

							<!-- ----------------  start ---------------- -->
							<div class="form-group">
								<label for="inputStart" class="col-md-2 control-label">Start date</label>
								<div class="col-md-10">
									<input class="form_datetime" id="inputStart"
										placeholder="Start Date" type="text" name="start"
										value="<fmt:formatDate value="${task.start}" pattern="dd.MM.yyyy HH:mm"/>">
								</div>
							</div>

							<!--  ----------------  end ---------------- -->
							<div class="form-group">
								<label for="inputEnd" class="col-md-2 control-label">End date</label>
								<div class="col-md-10">
									<input class="form_datetime" id="inputEnd"
										placeholder="Start Date" type="text" name="end"
										value="<fmt:formatDate value="${task.end}" pattern="dd.MM.yyyy HH:mm"/>">
								</div>
							</div>

							<!-- ----------------  place ---------------- -->
							<div class="form-group">
								<label for="inputPlace" class="col-md-2 control-label">Place</label>
								<div class="col-md-10">
									<input class="form-control" id="inputPlace" type="text"
										name="place"
										value="<c:out value="${task.place}"/>">
								</div>
							</div>

							<!-- ----------------  userName ---------------- -->
							<div class="form-group">
								<label for="inputUserName" class="col-md-2 control-label">userName</label>
								<div class="col-md-10">
									<input class="form-control" id="inputUserName" type="text"
										name="userName"
										value="<c:out value="${username}"/>">
								</div>
							</div>


							<!-- ----------------  buttons ---------------- -->
							<div class="form-group">
								<div class="col-md-10 col-md-offset-2">
									<button type="submit" class="btn btn-primary">Submit</button>
									<a href="task">
										<button type="button" class="btn btn-default">Cancel</button>
									</a>
								</div>
							</div>
						</fieldset>
						<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
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
			format : "dd.mm.yyyy hh:ii",
			autoclose : true,
			todayBtn : false,
			pickerPosition : "bottom-left",
			minuteStep: 5,
			startDate: new Date()
		});

	});
</script>


<jsp:include page="includes/templateEnd.jsp" />
