<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<fmt:message key="list_doctors_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<table align="center">
		<thead>
			<tr>
				<td> <fmt:message key="list_doctors_jsp.table.header.login" /> </td>
				<td> <fmt:message key="list_doctors_jsp.table.header.first_name" /></td>
				<td> <fmt:message key="list_doctors_jsp.table.header.last_name" /> </td>
				<td> <fmt:message key="list_doctors_jsp.table.header.specialization" /> </td>
				<td> <fmt:message key="list_doctors_jsp.table.header.count_of_patients" /> </td>
			</tr>
		</thead>
		<c:forEach var="bean" items="${traumatologistDoctorsBeanList}">
			<tr>									
				<td>${bean.login}</td>
				<td>${bean.firstName}</td>
				<td>${bean.lastName}</td>
				<td>${bean.specialization}</td>
				<td>${bean.countOfPatients}</td>
			
			</tr>
		</c:forEach>
	</table>								
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>