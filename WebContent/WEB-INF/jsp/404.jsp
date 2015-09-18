<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<fmt:message key="404_jsp.title" var="title" /> 
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>			

	<div align="center" id="fnf">
		<img src="<c:url value='/img/404.jpg'/>" width="100%" height="600" />
	</div>
							
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</body>
</html>