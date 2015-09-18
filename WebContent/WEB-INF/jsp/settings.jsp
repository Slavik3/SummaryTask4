<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<fmt:message key="settings_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>

				<form id="settings_form" action="controller" method="post">
					<input type="hidden" name="command" value="updateSettings" />

					<div>
						<p>
							<fmt:message key="settings_jsp.label.localization" />
						</p>
						<select name="localeToSet">
							<c:choose>
								<c:when test="${not empty defaultLocale}">
									<option value="">${defaultLocale}[Default]</option>
								</c:when>
								<c:otherwise>
									<option value="" />
								</c:otherwise>
							</c:choose>

							<c:forEach var="localeName" items="${locales}">
								<option value="${localeName}">${localeName}</option>
							</c:forEach>
						</select>
					</div>
					
					<c:forEach var="localeName" items="${locales}">								
						<a href="controller?command=updateSettings&localeToSet=${localeName}">
							<c:if test="${localeName == 'ru'}" >							
								<img src=http://pristavochka.com.ua/flag-rossii-300x189_60x38.jpg width="30" height="15"> 
							</c:if>
							<c:if test="${localeName == 'en'}" >							
								<img src="http://pristavochka.com.ua/images_60x32.jpg" width="30" height="15"> 
							</c:if>
						</a>
						<a href="controller?command=updateSettings&localeToSet=${localeName}">${localeName}</a>
					</c:forEach>


					<input type="submit"
						value='<fmt:message key="settings_jsp.button.update"/>'><br />
				</form> <%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>
</body>
</html>