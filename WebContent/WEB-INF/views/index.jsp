<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="includes/templateStart.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$('#calendar').fullCalendar({
				// put your options and callbacks here
				events : 'calendarJson', // url
				timeFormat : 'HH:mm',
				firstDay : 1, // 1 == Monday
				height: 250,
				defaultView: 'basicWeek'
			})

		});
	</script>
	
<div id="page-wrapper">
<jsp:include page="includes/messages.jsp" />

	
	<!-- calendar div is automatically populated -->
	<div id="calendar"></div>
</div>


<jsp:include page="includes/templateEnd.jsp" />