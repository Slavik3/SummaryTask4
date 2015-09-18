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
			<td><a href="controller?command=sortPatientsByName"><fmt:message key="list_doctors_jsp.table.header.first_name" /></a></td>
			<td><fmt:message key="list_doctors_jsp.table.header.last_name" /></td>
			<td><a href="controller?command=sortPatientsByBirthday"><fmt:message key="list_patients_jsp.table.header.birthday" /></a></td>						
			<td><fmt:message key="list_patients_jsp.table.to_fasten_a_patient_to_doctor" /></td>
		</tr>
	</thead>
	<c:forEach var="bean" items="${patientsBeanList}">
		<tr>				
			<td>${bean.firstName}</td>
			<td>${bean.lastName}</td>			
			<td><fmt:formatDate value="${bean.birthday}" pattern="dd.MM.yyyy"/></td>				
			<td><c:if test="${bean.doctorId==0}">
				<a href="controller?command=viewAssignThePatientToTheDoctor&id=${bean.id}"><fmt:message key="list_patients_jsp.table.choose_a_doctor" /></a></c:if>
			</td>	
		</tr>
	</c:forEach>
</table>
<br><br>
<c:if test="${message == 'Patient assigned'}" >
	<div id="coolmenu" align="center" class="successMsg">	
		<fmt:message key="list_patients_jsp.message.assigned" />	
	</div>		
</c:if>											
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>