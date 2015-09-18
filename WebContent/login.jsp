<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<%@ include file="WEB-INF/jspf/head.jspf"%>
<c:set var="title" value="Login" />
<style>
#login-box {
	width: 300px;
	padding: 20px;
	margin: 60px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<body>
	<script type="text/javascript">		
	<%@ include file="/WEB-INF/js/validationLogin.js"%>		
	</script>
	<div align="center">
		<img src="img/header.jpg" width="100%" height="249" />
	</div>	
	<nav class="navbar navbar-inverse" >

	</nav>
	<div id="login-box" align="center">
		<form id="login_form" action="controller" method="post" name="frm"	onsubmit="return validateForm()">
			<input type="hidden" name="command" value="login" /> 				
				<div class="input-group">	
					<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>							
					<input type='text' name='login' class="form-control" placeholder="Логин" id="name"> 
				</div>
				<br>				
				<div class="input-group">				
					<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>				
					<input type='password' name='password' class="form-control"	placeholder="Пароль" id="pass"> 
				</div>
				<br> 
				<input name="submit" type="submit" value="Войти" class="btn btn-primary btn-mini" />
		</form>		
	</div>	
	
	<c:if test="${errorMessage == 'Login/password cannot be empty_'}" >
		<div id="coolmenu" align="center" class="errorMsg">
			<fmt:message key="login_jsp.message.login_password_cannot_be_empty" />
		</div>		
	</c:if>	
	<c:if test="${errorMessage == 'Cannot find user with such login/password'}" >
		<div id="coolmenu" align="center" class="errorMsg">
			<fmt:message key="login_jsp.message.cannot_find_user_with_such_login_password" />
		</div>		
	</c:if>	
	
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>