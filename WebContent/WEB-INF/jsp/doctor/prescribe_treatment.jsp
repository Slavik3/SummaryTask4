<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<fmt:message key="prescribe_treatment_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
 <script type="text/javascript">
	<%@ include file="/WEB-INF/js/validationPrescribeTreatment.js"%>
</script>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>			
<div align="center">
<form id="inputUser"  method="post" action="controller?command=addTreatmentToHospitalCard" name="frm" onsubmit="return validateForm();">	
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.first_name" /></label>					
	<input value="${patient.getFirstName() }" size="50" name="firstName" id="firstName" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.first_name" />" readonly>
	<br>
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.last_name" /></label>					
	<input value="${patient.getLastName() }" size="50" name="lastName" id="lastName" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.last_name" />" readonly>
	<br>	
	<label class="myLabel"><fmt:message key="prescribe_treatment_jsp.label.diagnosis" /></label>			
	<input value="${patient.getDiagnos() }" size="50" name="diagnosis" id="diagnosis" class="form-control myInput" placeholder="<fmt:message key="prescribe_treatment_jsp.label.diagnosis" />">
	<br>
	<label class="myLabel"><fmt:message key="prescribe_treatment_jsp.label.type_of_treatment" /></label>	
		<select class="form-control myInput" name="typeOfTreatment">
		<c:forEach var="bean" items="${typeOfTreatmentBeanList}">
			<option>${bean.title}</option>
		</c:forEach>		
	</select>
	<br>
	
	<label class="myLabel"><fmt:message key="prescribe_treatment_jsp.label.title_treatment" /></label>			
	<input size="50" name="nameOfMedication" id="nameOfMedication" class="form-control myInput" placeholder="">
	<br>		
	<button name="submit" id="submit" value="" type="submit" class="btn btn-primary btn-mini" ><fmt:message key="prescribe_treatment_jsp.button.prescribe_treatment" /></button>
</form>
</div>							
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>