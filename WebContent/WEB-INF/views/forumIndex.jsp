<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<jsp:include page="includes/templateStart.jsp" />

<div class="container" role="main">
	<br>
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
			<h3 class="blank1">Forum</h3>
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<p margin-left="auto" margin-right="auto">

						<a href="fillForumEntrys">

							<button type="button" class="btn btn-primary">Fill Forum</button>
						</a> <a href="addForumEntry">

							<button type="button" class="btn btn-primary">New Entry</button>
						</a>
					</p>
					<br>
				</div>
			</div>
			<div class="xs tabls">
				<div class="panel-body1">
					<table class="table">
						<c:forEach items="${forumEntrys}" var="entry">

							<thead>
								<tr>
									<th style="background-color: #FFFFFF"></th>
									<th style="background-color: #FFFFFF"></th>
									<th style="background-color: #FFFFFF"></th>
									<th style="background-color: #FFFFFF"></th>

								</tr>
								<tr>


									<th style="background-color: #B7E97D;">Topic</th>
									<th style="background-color: #B7E97D;">Text</th>
									<th style="background-color: #B7E97D;">Attachment</th>
									<th style="background-color: #B7E97D;">Author</th>
									<th style="background-color: #B7E97D;">Action</th>


								</tr>
							</thead>
							<tbody>

								<tr>
									<td style="background-color: #E6F9D0;">${entry.topic}</td>
									<td style="background-color: #E6F9D0;">${entry.text}</td>
									<td style="background-color: #E6F9D0;"><c:choose>
											<c:when test="${not empty entry.attachment}">
												<a href="download?attachmentId=${entry.attachment.id}"
													target="_blank">${entry.attachment.filename}</a>
											</c:when>
											<c:otherwise>
												-- no Attachment ---
											</c:otherwise>
										</c:choose></td>
									<td style="background-color: #E6F9D0;">${entry.userName}</td>
									
									<td style="background-color: #E6F9D0;"><a
										href="editForumEntry?id=${entry.id}"><button type="button"
												class="btn btn-s btn-warning">Edit</button></a> <a
										href="deleteForumEntry?id=${entry.id}"><button
												type="button" class="btn btn-s btn-danger">Delete</button></a></td>




								</tr>

							</tbody>
						</c:forEach>

					</table>

				</div>

				<!-- /.table-responsive -->
			</div>
		</div>
	</div>
</div>

<jsp:include page="includes/templateEnd.jsp" />