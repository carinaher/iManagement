<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="includes/templateStart.jsp" />

<div id="page-wrapper">

	<h1>iManagement</h1>
	<p>
		<a href="student"><button type="button" class="btn btn-success">Student List</button></a>
	</p>
	<br>
	<p>
		<a href="task"><button type="button" class="btn btn-success">Task List</button></a>
	</p>
	<br>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p>
			<a href="user"><button type="button" class="btn btn-success">User List</button></a>
		</p>
		<br>		
		<p>
			<a href="userrole"><button type="button" class="btn btn-success">User Role List</button></a>
		</p>
	</sec:authorize>
	<br>
</div>


<jsp:include page="includes/templateEnd.jsp" />