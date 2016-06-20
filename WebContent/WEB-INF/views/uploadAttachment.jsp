
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="includes/templateStart.jsp" />

<title>Upload Attachment</title>

<div id="page-wrapper">
	<div class="graphs">
		<h3 class="blank1">Upload Attachment</h3>
		<div class="xs tabls">
			<div class="bs-example4" data-example-id="contextual-table">

				<div class="container" role="main">

					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<form class="form-horizontal" enctype="multipart/form-data"
								method="post" action="./upload?${_csrf.parameterName}=${_csrf.token}">
								<fieldset>

									<! ----------------  id ---------------- -->
									<div class="form-group">
										<input type="hidden" name="id"
											value="<c:out value="${entryId}"/>">
									</div>
									<! ----------------  file ---------------- -->

									<div class="form-group">
										<input id="inputFile" type="file" name="myFile">
										<p class="help-block">Please select the file, you would
											like to upload to the forum.</p>
									</div>

									<! ----------------  buttons ---------------- -->
									<div class="form-group">
										<div class="col-md-10 col-md-offset-2">
											<br>
											<button type="submit" class="btn btn-primary">Upload</button>
											<a href="forum">
												<button type="button" class="btn btn-default">Cancel</button>
											</a>
										</div>
									</div>
								</fieldset>
								<input type="hidden" name="${_csrf.parameterName }"
									value="${_csrf.token }" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




</body>
<jsp:include page="includes/templateEnd.jsp" />