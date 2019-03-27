<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization" var="loc"/>

<fmt:message bundle="${loc}" key="main" var="main"/>
<fmt:message bundle="${loc}" key="language" var="language"/>
<fmt:message bundle="${loc}" key="registration" var="registration"/>
<fmt:message bundle="${loc}" key="signIn" var="signIn"/>
<fmt:message bundle="${loc}" key="ru" var="ru"/>
<fmt:message bundle="${loc}" key="en" var="en"/>
<fmt:message bundle="${loc}" key="signOut" var="signOut"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../styles/bootstrap.min.css">
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript"
            src="../../js/popper.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
    <link rel='stylesheet prefetch'
          href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="../../../styles/constant_part.css">
</head>
<body>
<nav class="navbar navbar-expand-sm" id="navbar">
    <img class="navbar-brand" src="images/logo.jpeg">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="fa fa-navicon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
        <div>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/index.jsp">${main}</a>
                </li>
            </ul>
        </div>
        <div>
            <ul class="navbar-nav">
                <li id="lang" class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        ${language}
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/index?command=change_language&language=ru">${ru}</a>
                        <a class="dropdown-item" href="/index?command=change_language&language=en">${en}</a>
                    </div>
                </li>
                <c:if test="${sessionScope.user ==null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/index?command=go_to_registration">${registration}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user!=null}">
                </c:if>
                <c:if test="${sessionScope.user ==null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/index?command=go_to_authorization">${signIn}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user!=null}">
                    <li class="nav-item">
                        <a class="nav-link" href="/index?command=sign_out">${signOut}</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>