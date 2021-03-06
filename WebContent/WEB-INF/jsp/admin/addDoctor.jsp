<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<fmt:message key="add_doctor_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<script type="text/javascript">
<%@ include file="/WEB-INF/js/validationAddDoctor.js"%>
</script>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div align="center">
<form id="inputUser"  method="post" action="controller?command=addDoctor" name="frm" onsubmit="return validateForm();">	
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.first_name" /></label>
	<input size="50" name="firstName" id="firstName" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.first_name" />">
	
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.last_name" /></label>					
	<input size="50" name="lastName" id="lastName" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.last_name" />">
	
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.login" /></label>					
	<input size="50" name="login" id="login" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.login" />">
		
	<label class="myLabel"><fmt:message key="add_doctor_jsp.password" /></label>					
	<input size="50" name="password" id="password" type="password" class="form-control myInput" placeholder="<fmt:message key="add_doctor_jsp.password" />">						
	
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.specialization" /></label>		
	<select class="form-control myInput" name="specialization">
		<c:forEach var="bean" items="${specializationDoctorsBeanList}">
			<option>${bean.title}</option>
		</c:forEach>	
	</select>
	<br>	
	<button name="submit" id="submit" value="" type="submit" class="btn btn-primary btn-mini"><fmt:message key="add_doctor_jsp.button.add" /></button>	
</form>
</div>	
<c:if test="${message == 'Doctor added'}" >
	<div id="coolmenu" align="center" class="successMsg">	
		<fmt:message key="add_doctor_jsp.message.added" />	
	</div>		
</c:if>
<c:if test="${message == 'Doctor no added'}" >
	<div id="coolmenu" align="center" class="errorMsg">		
		<fmt:message key="add_doctor_jsp.message.not_added" />		
	</div>		
</c:if>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>