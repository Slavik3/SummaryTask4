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
				<td> <fmt:message key="list_doctors_jsp.table.header.first_name" /> </td>
				<td> <fmt:message key="list_doctors_jsp.table.header.last_name" /> </td>
				<td> <fmt:message key="list_doctors_jsp.table.header.specialization" /> </td>
			</tr>
		</thead>
		<c:forEach var="bean" items="${doctorsBeanList}">
			<tr>									
				<td>${bean.login}</td>
				<td>${bean.firstName}</td>
				<td>${bean.lastName}</td>
				<td>${bean.specialization}</td>
				<td><a href="controller?command=assignThePatientToTheDoctor&doctor_id=${bean.id}&id=${param.id}"><fmt:message key="list_doctors_jsp.table.assign" /></a> </td>
								
			</tr>
		</c:forEach>
	</table>								
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>