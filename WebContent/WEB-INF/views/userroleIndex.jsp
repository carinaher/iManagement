<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="includes/templateStart.jsp" />
<div class="container" role="main">

<jsp:include page="includes/messages.jsp" />

	<div id="page-wrapper">
		<div class="graphs">
			<h3 class="blank1">User Roles</h3>
			<div class="xs tabls">
				<div class="bs-example4" data-example-id="contextual-table">
					<div class="row">
						<div class="col-md-4">
							<p>
								<a href="addUserrole">
									<button type="button" class="btn btn-primary">
										<span aria-hidden="true"></span>Add User Role
									</button>
								</a>
							</p>
						</div>
					</div>
					<br>
					<table class="table">
						<thead>
							<tr>
								<th>User Role Id</th>
								<th>User (Username)</th>
								<th>Role</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userroles}" var="userrole">
								<tr>

									<th scope="row">${userrole.userRoleId}</th>
									<td>${userrole.user.userName}</td>
									<td>${userrole.role}</td>
									<td><a href="editUserrole?userRoleId=${userrole.userRoleId}"><button
												type="button" class="btn btn-s btn-warning">Edit</button></a>
												
										
										<form class="form-horizontal" method="post" action="deleteUserrole">
											<fieldset>
												<button type="submit" class="btn btn-s btn-danger">Delete</button>
											</fieldset>
											<input type="hidden" name="userRoleId" value="${userrole.userRoleId}" />
											<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
										</form>
												
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