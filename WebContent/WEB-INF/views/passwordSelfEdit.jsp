<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp" />


<c:set var="legend">Change password for user "${user.username}"</c:set>
<c:set var="readonly"></c:set>


<div id="page-wrapper">
	<jsp:include page="includes/messages.jsp" />
	<div class="graphs">
		<h3 class="blank1">${legend}</h3>
		<div class="xs tabls">
			<div class="bs-example4" data-example-id="contextual-table">

				<table class="table">
					
												
						
					<form class="form-horizontal" method="post"  data-toggle="validator">
					<fieldset>
						
						<!-- old password -->										
					    <div class="form-group">
					    	<label for="oldPassword" class="col-md-2 control-label">Old Password</label>
					    	<div class="col-md-10">
						    	<input type="password" class="form-control col-md-2" id="oldPassword" name="oldPassword" placeholder="Old Password" required>
						    	<!-- <div class="help-block">Minimum of 6 characters</div> -->
					    	</div>
					    </div>
						
						<!-- new password -->										
					    <div class="form-group">
					    	<label for="newPassword" class="col-md-2 control-label">Password</label>
					    	<div class="col-md-10">
						    	<input type="password" class="form-control col-md-2" id="newPassword" name="newPassword" placeholder="New Password" required>
						    	<!-- <div class="help-block">Minimum of 6 characters</div> -->
					    	</div>
					    </div>
					    
						<!-- new password confirmation -->	
					    <div class="form-group">
					    	<label for="newPasswordConfirmation" class="col-md-2 control-label">Confirm new password</label>
					    	<div class="col-md-10">					    	
					    		<input type="password" class="form-control col-md-2" id="newPasswordConfirmation" name="newPasswordConfirmation" data-match="newPassword" data-match-error="New passwords don't match!" placeholder="Confirm password" required>
					    		<div class="help-block with-errors"></div>
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
										<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
					
				</form>
				</table>
			</div>

			<!-- /.table-responsive -->
		</div>
	</div>
</div>
<jsp:include page="includes/templateEnd.jsp" />