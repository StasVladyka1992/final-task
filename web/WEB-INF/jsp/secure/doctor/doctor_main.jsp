<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 18.02.2019
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../../../../styles/user_main.css">
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="writePrescription" var="writePrescription"/>
<fmt:message bundle="${loc}" key="writePrescriptionMessage" var="writePrescriptionMessage"/>
<fmt:message bundle="${loc}" key="further" var="further"/>
<fmt:message bundle="${loc}" key="updatePersonalInfo" var="updatePersonalInfo"/>
<fmt:message bundle="${loc}" key="updatePersonalInfoMessage" var="updatePersonalInfoMessage"/>
<div class="container">
    <div class="d-flex flex-wrap justify-content-around mt-1">
        <div class="card mt-1 mb-1">
            <img class="card-img-top" src="../../../../images/sign_receipt.jpeg">
            <div class="card-body">
                <h5 class="card-title text-center">${writePrescription}</h5>
                <p class="card-text">${writePrescriptionMessage}</p>
                <div class="d-flex justify-content-around">
                    <a href="/secure?command=show_unhandled_applications" type="button" class="btn btn-success">${further}</a>
                </div>
            </div>
        </div>
        <div class="card mt-1 mb-1">
            <img class="card-img-top" src="../../../../images/personal_data.jpeg">
            <div class="card-body">
                <h5 class="card-title text-center">${updatePersonalInfo}</h5>
                <p class="card-text">${updatePersonalInfoMessage}</p>
                <div class="d-flex justify-content-around">
                    <a href="#" type="button" class="btn btn-success ">${further}</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>