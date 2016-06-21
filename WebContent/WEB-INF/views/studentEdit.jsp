<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp" />

<!--  add or edit?  ----------------------------------------------------------- -->
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
<!--  add or edit?  ----------------------------------------------------------- -->


<div id="page-wrapper">
	<div class="graphs">
		<h3 class="blank1">${legend}</h3>
		<div class="xs tabls">
			<div class="bs-example4" data-example-id="contextual-table">

				<table class="table">
					<form class="form-horizontal" method="post" action="${formAction}">
						<fieldset>

							<c:if test="${not empty student}">
								<!-- ----------------  Id ---------------- -->
								<div class="form-group">
									<label for="inputId" class="col-md-2 control-label">Id</label>
									<div class="col-md-10">
										<input class="form-control" id="inputId" type="text" name="id"
											${readonly} value="<c:out value="${student.id}"/>">
									</div>
								</div>
							</c:if>

							<!-- ----------------  userName ---------------- -->
							<div class="form-group">
								<label for="inputUserName" class="col-md-2 control-label">Username</label>
								<div class="col-md-10">
									<input class="form-control" id="inputUserName" type="text"
										name="userName" value="<c:out value="${student.userName}"/>">
								</div>
							</div>

							<!-- ----------------  firstName ---------------- -->
							<div class="form-group">
								<label for="inputFirstName" class="col-md-2 control-label">First
									Name</label>
								<div class="col-md-10">
									<input class="form-control" id="inputFirstName" type="text"
										name="firstName" value="<c:out value="${student.firstName}"/>">
								</div>
							</div>

							<!-- ----------------  lastName ---------------- -->
							<div class="form-group">
								<label for="inputLastName" class="col-md-2 control-label">Last
									Name</label>
								<div class="col-md-10">
									<input class="form-control" id="inputLastName" type="text"
										name="lastName" value="<c:out value="${student.lastName}"/>">
								</div>
							</div>

							<!-- ----------------  githubUser ---------------- -->
							<div class="form-group">
								<label for="inputGithubUser" class="col-md-2 control-label">Github
									User</label>
								<div class="col-md-10">
									<input class="form-control" id="inputGithubUser" type="text"
										name="githubUser"
										value="<c:out value="${student.githubUser}"/>">
								</div>
							</div>

							<!-- ----------------  eMail ---------------- -->
							<div class="form-group">
								<label for="inputEMail" class="col-md-2 control-label">E-Mail</label>
								<div class="col-md-10">
									<input class="form-control" id="inputEMail" type="text"
										name="eMail" value="<c:out value="${student.eMail}"/>">
								</div>
							</div>

							<!-- ----------------  year ---------------- -->
							<div class="form-group">
								<label for="inputYear" class="col-md-2 control-label">Year</label>
								<div class="col-md-10">
									<input class="form-control" id="inputYear" type="text"
										name="year" value="<c:out value="${student.year}"/>">
								</div>
							</div>

							<!-- ----------------  groupId ---------------- -->
							<div class="form-group">
								<label for="inputGroupId" class="col-md-2 control-label">Group
									ID</label>
								<div class="col-md-10">
									<input class="form-control" id="inputGroupId" type="text"
										name="groupId" value="<c:out value="${student.groupId}"/>">
								</div>
							</div>

							<!-- ----------------  buttons ---------------- -->
							<div class="form-group">
								<div class="col-md-10 col-md-offset-2">
									<button type="submit" class="btn btn-primary">Submit</button>
									<a href="student">
										<button type="button" class="btn btn-default">Cancel</button>
									</a>
								</div>
							</div>
						</fieldset>
						<input type="hidden" name="${_csrf.parameterName }"
							value="${_csrf.token }" />
					</form>
				</table>
			</div>

			<!-- /.table-responsive -->
		</div>
	</div>
</div>
<jsp:include page="includes/templateEnd.jsp" />