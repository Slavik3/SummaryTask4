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
				<td><a href="controller?command=prescribeTreatment&patientId=${bean.id}"><fmt:message key="list_patients_doctor_jsp.table.prescribe_treatment" /></a></td>		
				<td><a href="controller?command=viewToPerformDisinfection&patientId=${bean.id}"><fmt:message key="list_patients_doctor_jsp.table.to_perform_treatment" /></a></td>			
				<td><a href="controller?command=completeTheCourseOfTreatment&patientId=${bean.id}"><fmt:message key="list_patients_doctor_jsp.table.discharged_from_hospital" /></a></td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<c:if test="${message == 'The patient is discharged'}" >
	<div id="coolmenu" align="center" class="successMsg">
		<fmt:message key="list_patients_jsp.message.discharged" />
	</div>		
</c:if>
<c:if test="${message == 'You need to make at least one appointment'}" >
	<div id="coolmenu" align="center" class="errorMsg">
			<fmt:message key="list_patients_jsp.message.not_discharged" />
	</div>		
</c:if>
<c:if test="${message == 'Treatment assignment'}" >
	<div id="coolmenu" align="center" class="successMsg">	
		<fmt:message key="list_patients_jsp.message.treatment_assignment" />	
	</div>		
</c:if>	
<c:if test="${message == 'Treatments performed'}" >
	<div id="coolmenu" align="center" class="successMsg">	
		<fmt:message key="list_patients_jsp.message.treatment_performed" />	
	</div>		
</c:if>	
									
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>