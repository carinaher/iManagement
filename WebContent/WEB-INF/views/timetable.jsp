<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="includes/templateStart.jsp" />


<c:set var="legend">Timetable for "${user.username}"</c:set>


<%-- groups to hide from timetable --%>
<c:choose>
	<c:when test="${student.groupId == 1}">
		<c:set var="hiddenGroup">G2</c:set>
	</c:when>
	<c:when test="${student.groupId == 2}">
		<c:set var="hiddenGroup">G1</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="hiddenGroup"></c:set>
	</c:otherwise>
</c:choose>

<div id="page-wrapper">
	<jsp:include page="includes/messages.jsp" />
	<div class="graphs">
		<h3 class="blank1">${legend}</h3>
		<div class="xs tabls">
			
			<c:choose>
				<c:when test="${not empty student}">
					<iframe style="width:1020px;height:670px" src="http://stundenplan.fh-joanneum.at/index.php?user=ima&pass=ima&login=Login&new_jg=20${student.year}&new_nodraw_array=${hiddenGroup}"></iframe>
				</c:when>
				<c:otherwise>
					<p>No student associated with your account.</p>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
</div>
<jsp:include page="includes/templateEnd.jsp" />