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
		</tr>
	</thead>
	<c:forEach var="bean" items="${patientsBeanList}">
		<tr>				
			<td>${bean.firstName}</td>
			<td>${bean.lastName}</td>			
			<td><fmt:formatDate value="${bean.birthday}" pattern="dd.MM.yyyy"/></td>
			<td>${bean.diagnos}</td>
			<td><a href="controller?command=viewToPerformTreatmentByNurse&patientId=${bean.id}"><fmt:message key="list_patients_doctor_jsp.table.to_perform_treatment" /></a></td>			
		</tr>
	</c:forEach>
</table>
<br><br>
<c:if test="${message == 'Treatments performed'}" >
	<div id="coolmenu" align="center" class="successMsg">	
		<fmt:message key="list_patients_jsp.message.treatment_performed" />	
	</div>		
</c:if>								
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>