<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="includes/templateStart.jsp" />
<div class="container" role="main">
	<jsp:include page="includes/messages.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$('#calendar').fullCalendar({
				// put your options and callbacks here
				events : 'calendarJson', // url
				timeFormat : 'HH:mm',
				firstDay : 1, // 1 == Monday
				height: 650
			})

		});
	</script>

	<div id="page-wrapper">
		<div class="graphs">
			<p>
				<a href="fillTasks">
					<button type="button" class="btn btn-primary">
						<span aria-hidden="true"></span>Fill Tasks
					</button>
				</a>
				<a href="addTask">
					<button type="button" class="btn btn-primary">
						<span aria-hidden="true"></span>Add Task
					</button>
				</a>
			</p>
			<!-- calendar div is automatically populated -->
			<div id="calendar"></div>
		</div>
	</div>
</div>

<jsp:include page="includes/templateEnd.jsp" />