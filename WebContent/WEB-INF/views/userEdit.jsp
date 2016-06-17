<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp" />

<!--  add or edit?  ----------------------------------------------------------- -->
<c:choose>
	<c:when test="${not empty user}">
		<c:set var="legend">Edit User ${user.userName}</c:set>
		<c:set var="formAction">editUser</c:set>
		<c:set var="readonly">readonly</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="legend">New User</c:set>
		<c:set var="formAction">addUser</c:set>
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
							<!-- ----------------  userName ---------------- -->
							<div class="form-group">
								<label for="inputUserName" class="col-md-2 control-label">UserName</label>
								<div class="col-md-10">
									<input class="form-control" id="inputUserName" type="text"
										name="userName" value="<c:out value="${user.userName}"/>">
								</div>
							</div>

							<!-- ----------------  password ---------------- -->
							<div class="form-group">
								<label for="inputPassword" class="col-md-2 control-label">Password</label>
								<div class="col-md-10">
									<input class="form-control" id="inputPassword" type="text"
										name="password" value="<c:out value="${user.password}"/>">
								</div>
							</div>

							<!-- ----------------  enabled ---------------- -->
							<div class="form-group">
								<label for="inputEnabled" class="col-md-2 control-label">Enabled</label>
								<div class="col-md-10">
									<input class="form-control" id="inputEnabled" type="text"
										name="enabled" value="<c:out value="${user.enabled}"/>">
								</div>
							</div>

							<!-- ----------------  buttons ---------------- -->
							<div class="form-group">
								<div class="col-md-10 col-md-offset-2">
									<button type="submit" class="btn btn-primary">Submit</button>
									<a href="user">
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