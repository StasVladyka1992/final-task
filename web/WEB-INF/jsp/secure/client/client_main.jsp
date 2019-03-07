<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 18.02.2019
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../../../../styles/user_main.css">
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="remedySearching" var="remedySearching"/>
<fmt:message bundle="${loc}" key="remedySearchingMessage" var="remedySearchingMessage"/>
<fmt:message bundle="${loc}" key="further" var="further"/>
<fmt:message bundle="${loc}" key="appealForReceipt" var="appealForReceipt"/>
<fmt:message bundle="${loc}" key="appealForReceiptMessage" var="appealForReceiptMessage"/>
<fmt:message bundle="${loc}" key="updatePersonalInfo" var="updatePersonalInfo"/>
<fmt:message bundle="${loc}" key="updatePersonalInfoMessage" var="updatePersonalInfoMessage"/>
<div class="container">
    <div class="d-flex flex-wrap justify-content-around mt-1">
        <div class="card mt-1 mb-1">
            <img class="card-img-top" src="../../../../images/remedy_searching.jpeg">
            <div class="card-body">
                <h5 class="card-title text-center">${remedySearching}</h5>
                <p class="card-text">${remedySearchingMessage}</p>
                <div class="d-flex justify-content-around">
                    <a href="/secure?command=go_to_remedy" type="button" class="btn btn-success">${further}</a>
                </div>
            </div>
        </div>
        <div class="card mt-1 mb-1">
            <img class="card-img-top" src="../../../../images/doctor_receipt.jpg">
            <div class="card-body">
                <h5 class="card-title text-center">${appealForReceipt}</h5>
                <p class="card-text">${appealForReceiptMessage}</p>
                <div class="d-flex justify-content-around">
                    <a href="#" type="button" class="btn btn-success">${further}</a>
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
