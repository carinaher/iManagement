<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp" />

<!--  add or edit?  ----------------------------------------------------------- -->
<c:choose>
	<c:when test="${not empty userRole}">
		<c:set var="legend">Edit User Role: # ${userRole.userRoleId}</c:set>
		<c:set var="formAction">editUserrole</c:set>
		<c:set var="readonly">readonly</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="legend">New User Role</c:set>
		<c:set var="formAction">addUserrole</c:set>
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
							<!-- ----------------  userRoleId ---------------- -->
							<div class="form-group">
								<label for="inputUserRoleId" class="col-md-2 control-label">User Role ID</label>
								<div class="col-md-10">
									<input class="form-control" id="inputUserRoleId" type="text"
										name="userRoleId" value="<c:out value="${userRole.userRoleId}"/>">
								</div>
							</div>

							<!-- ----------------  User (userName) ---------------- -->
							<div class="form-group">
								<label for="inputUserName" class="col-md-2 control-label">User (Username)</label>
								<div class="col-md-10">
									<input class="form-control" id="inputUserName" type="text"
										name="userName" value="<c:out value="${userRole.user.userName}"/>">
								</div>
							</div>

							<!-- ----------------  role ---------------- -->
							<div class="form-group">
								<label for="inputRole" class="col-md-2 control-label">Role</label>
								<div class="col-md-10">
									<input class="form-control" id="inputRole" type="text"
										name="role" value="<c:out value="${userRole.role}"/>">
								</div>
							</div>

							<!-- ----------------  buttons ---------------- -->
							<div class="form-group">
								<div class="col-md-10 col-md-offset-2">
									<button type="submit" class="btn btn-primary">Submit</button>
									<a href="userrole">
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