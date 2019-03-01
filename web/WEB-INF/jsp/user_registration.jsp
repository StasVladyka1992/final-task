<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="constant_part/navbar.jsp" %>

<fmt:message bundle="${loc}" key="email" var="email"/>
<fmt:message bundle="${loc}" key="firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="phone" var="phone"/>
<fmt:message bundle="${loc}" key="password" var="password"/>
<fmt:message bundle="${loc}" key="submit" var="submit"/>
<fmt:message bundle="${loc}" key="type" var="type"/>
<fmt:message bundle="${loc}" key="registrationForm" var="registrationForm"/>
<fmt:message bundle="${loc}" key="doctor" var="doctor"/>
<fmt:message bundle="${loc}" key="pharmacist" var="pharmacist"/>
<fmt:message bundle="${loc}" key="client" var="client"/>
<fmt:message bundle="${loc}" key="userExist" var="userExist"/>
<fmt:message bundle="${loc}" key="incorrectPassword" var="incorrectPassword"/>
<fmt:message bundle="${loc}" key="incorrectEmail" var="incorrectEmail"/>
<fmt:message bundle="${loc}" key="incorrectName" var="incorrectName"/>
<fmt:message bundle="${loc}" key="incorrectPhone" var="incorrectPhone"/>

<div class="container-fluid">
    <div class="card col-5 m-auto">
        <div class="card-body">
            <h4 class="card-title text-center">${registrationForm}</h4>
            <form action="/index?command=registration" method="post">
                <div class="form-group">
                    <label for="email">${email}</label>
                    <input type="email" id="email" onchange="checkeamil()" name="email" class="form-control" placeholder="newemail@mail.ru"
                           required maxlength="40"/>
                </div>
                <div class="form-group">
                    <label for="password">${password}:</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="${password}"
                           required maxlength="30">
                </div>

                <div class="form-group">
                    <label for="firstName">${firstName}</label>
                    <input type="text" id="firstName" name="firstName" class="form-control" placeholder="${firstName}"
                           required maxlength="30">
                </div>
                <div class="form-group">
                    <label for="lastName">${lastName}</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" placeholder="${lastName}"
                           required maxlength="30">
                </div>
                <div class="form-group">
                    <label for="phone">${phone}</label>
                    <input type="text" id="phone" name="phone" class="form-control" placeholder="+375-29-111-11-11"
                           required maxlength="17">
                </div>
                <div class="form-group">
                    <label for="type">${type}</label>
                    <select class="form-control" id="type" name="role">
                        <option value="p">${pharmacist} </option>
                        <option value="c">${client}</option>
                        <option value="d">${doctor}</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success" value="${submit}">
                </div>
            </form>
            <c:if test="${not empty param.userExist}">
                <p class="text-danger">
                    <c:out value="${userExist}"></c:out>
                </p>
            </c:if>
            <c:if test="${not empty param.incorrectName}">
                <p class="text-danger">
                    <c:out value="${incorrectName}"/>
                </p>
            </c:if>
            <c:if test="${not empty param.incorrectEmail}">
                <p class="text-danger">
                    <c:out value="${incorrectEmail}"/>
                </p>
            </c:if>
            <c:if test="${not empty param.incorrectPassword}">
                <p class="text-danger">
                    <c:out value="${incorrectPassword}"/>
                </p>
            </c:if>
            <c:if test="${not empty param.incorrectPhone}">
                <p class="text-danger">
                    <c:out value="${incorrectPhone}"/>
                </p>
            </c:if>
        </div>
    </div>
</div>
<div class="container-fluid" id="footer">
    <%@ include file="constant_part/footer.jsp" %>
</div>
</body>
</html>