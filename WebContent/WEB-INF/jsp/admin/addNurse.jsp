<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<fmt:message key="add_nurse_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<script type="text/javascript">
<%@ include file="/WEB-INF/js/validationAddNurse.js"%>
</script>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div align="center">
<form id="inputUser"  method="post" action="controller?command=addNurse" name="frm" onsubmit="return validateForm();">	
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.first_name" /></label>					
	<input size="50" name="firstName" id="firstName" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.first_name" />">
	<br>
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.last_name" /></label>					
	<input size="50" name="lastName" id="lastName" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.last_name" />">
	<br>
	<label class="myLabel"><fmt:message key="list_doctors_jsp.table.header.login" /></label>					
	<input size="50" name="login" id="login" class="form-control myInput" placeholder="<fmt:message key="list_doctors_jsp.table.header.login" />">
	<br>		
	<label class="myLabel"><fmt:message key="add_doctor_jsp.password" /></label>					
	<input size="50" name="password" id="password" type="password" class="form-control myInput" placeholder="<fmt:message key="add_doctor_jsp.password" />">						
	<br>		
	<button name="submit" id="submit" value="" type="submit" class="btn btn-primary btn-mini" ><fmt:message key="add_nurse_jsp.button.add" /></button>	
</form>
</div>

<c:if test="${message == 'Nurse added'}" >
	<div id="coolmenu" align="center" class="successMsg">	
		<fmt:message key="add_nurse_jsp.message.added" />	
	</div>		
</c:if>
<c:if test="${message == 'Nurse no added'}" >
	<div id="coolmenu" align="center" class="errorMsg">
		<fmt:message key="add_nurse_jsp.message.not_added" />
	</div>		
</c:if>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>