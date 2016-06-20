<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src='resources/tinymce/tinymce.min.js'></script>

<script>
	tinymce
			.init({
				selector : '#editable',
				inline : false,
				plugins : [
						'advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker',
						'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
						'save table contextmenu directionality emoticons template paste textcolor' ],
				content_css : 'css/content.css',
				toolbar : 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | preview media fullpage | forecolor backcolor emoticons',
				skin : 'imanagement'
			});
</script>
<jsp:include page="includes/templateStart.jsp" />
<link
	href="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"
	rel="stylesheet">

<!--  add or edit?  ----------------------------------------------------------- -->
<c:choose>
	<c:when test="${not empty forumEntrys}">
		<c:set var="legend">Edit Task ${forumEntrys.id}</c:set>
		<c:set var="formAction">editForumEntry</c:set>
		<c:set var="readonly">readonly</c:set>
		<c:set var="upload">/uploadExistingEntry</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="legend">New Entry</c:set>
		<c:set var="formAction">addForumEntry</c:set>
		<c:set var="readonly"></c:set>
		<c:set var="upload">/uploadNewEntry</c:set>
	</c:otherwise>
</c:choose>
<!--  add or edit?  ----------------------------------------------------------- -->


<div id="page-wrapper">
	<div class="graphs">
		<h3 class="blank1">${legend}</h3>
		<div class="xs tabls">
			<div class="bs-example4" data-example-id="contextual-table">

				<table class="table">
					<form class="form-horizontal" method="post" action="${formAction} ">
						<fieldset>

							<! ----------------  Topic ---------------- -->
							<div class="form-group">
								<br> <label for="inputTopic" class="col-md-2 control-label">Topic</label>
								<div class="col-md-10">
									<input class="form-control" id="inputTopic" type="text"
										name="topic" value="<c:out value="${forumEntrys.topic}"/>">
								</div>
							</div>

							<! ----------------  Text ---------------- -->
							<div class="form-group">
								<br> <label for="inputText" class="col-md-2 control-label">Text</label>
								<div class="col-md-10">
									<textarea id="editable" name="text">
								<c:out value="${forumEntrys.text}" />
								</textarea>

								</div>
							</div>

							<! ----------------  Attachment ---------------- -->
							<div class="form-group">

								<label for="inputText" class="col-md-2 control-label"></label>
								<div class="col-md-10">
									<br> <a href="upload?id=${forumEntrys.id}">
										<button type="button" class="btn btn-xs btn-success">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
											Upload
										</button>
									</a>
								</div>
							</div>
							<! ----------------  buttons ---------------- -->
							<div class="form-group">
								<div class="col-md-10 col-md-offset-2">
									<br>
									<button type="submit" class="btn btn-primary">Submit</button>
									<a href="forum">
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
