<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<fmt:message key="list_patients_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>			
<table align="center">
	<thead>
		<tr>				
			<td><fmt:message key="list_doctors_jsp.table.header.first_name" /></td>
			<td><fmt:message key="list_doctors_jsp.table.header.last_name" /></td>
			<td><fmt:message key="list_patients_jsp.table.header.birthday" /></td>	
			<td><fmt:message key="list_patients_jsp.table.header.diagnosis" /></td>
			<td><fmt:message key="list_patients_jsp.table.patient_card" /></td>
		</tr>
	</thead>
	<c:forEach var="bean" items="${dischargedPatientsBeanList}">
		<tr>				
			<td>${bean.firstName}</td>
			<td>${bean.lastName}</td>			
			<td><fmt:formatDate value="${bean.birthday}" pattern="dd.MM.yyyy"/></td>
			<td>${bean.diagnos}</td>
			<td><a href="controller?command=downloadFile&patientName=${bean.firstName}&patientLastName=${bean.lastName}"><fmt:message key="list_patients_jsp.table.download" /></a></td>
		</tr>
	</c:forEach>
</table>								
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>