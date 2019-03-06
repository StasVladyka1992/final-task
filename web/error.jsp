<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 05.03.2019
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization" var="loc"/>
<fmt:message bundle="${loc}" key="errorMessage" var="errorMessage"/>
<fmt:message bundle="${loc}" key="errorTitle" var="errorTitle"/>
<html>
<body>
<h1>${errorTitle}</h1>
<c:out value="${errorMessage}"> </c:out>
</body>
</html>
