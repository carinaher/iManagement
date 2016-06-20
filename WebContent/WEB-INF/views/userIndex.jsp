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
			<h3 class="blank1">User Table</h3>
			<div class="xs tabls">
				<div class="bs-example4" data-example-id="contextual-table">
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<p>
								<a href="addUser">
									<button type="button" class="btn btn-primary">
										<span aria-hidden="true"></span>Add User
									</button>
								</a>
							</p>
						</div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>Username</th>
								<th>Password</th>
								<th>Enabled</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>

									<th scope="row">${user.userName}</th>
									<td>${user.password}</td>
									<td>${user.enabled}</td>
									<td><a href="editUser?userName=${user.userName}"><button
												type="button" class="btn btn-s btn-warning">Edit</button></a>
										<a href="deleteUser?userName=${user.userName}"><button
												type="button" class="btn btn-s btn-danger">Delete</button></a></td>
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