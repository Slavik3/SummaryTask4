<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<fmt:message key="to_perform_disinfection_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<table align="center">
		<thead>
			<tr>				
				<td><fmt:message key="to_perform_disinfection_jsp.table.header.last_name_of_the_patient" /></td>
				<td><fmt:message key="to_perform_disinfection_jsp.table.header.type_of_treatment_prescribed_by_the_patient" /></td>	
				<td><fmt:message key="to_perform_disinfection_jsp.table.header.the_specific_name_of_the_procedure_drugs_surgery" /></td>
			</tr>
		</thead>
		<c:forEach var="bean" items="${hospitalCardList}">
			<tr>
				<td>${bean.lastName}</td>					
				<td>${bean.typeOfTreatment}</td>			
				<td>${bean.nameOfMedication}</td>
				<!-- <td>${bean.done}</td> -->
				<c:if test="${not bean.done}">
					<td><a href="controller?command=doctorToPerformTreatment&id=${bean.id}"><fmt:message key="to_perform_disinfection_jsp.table.execute_appointment" /></a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>								
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>