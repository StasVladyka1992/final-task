<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 07.03.2019
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="remedyName" var="remedyName"/>
<fmt:message bundle="${loc}" key="quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="price" var="price"/>
<fmt:message bundle="${loc}" key="receipt" var="receipt"/>
<fmt:message bundle="${loc}" key="description" var="description"/>
<fmt:message bundle="${loc}" key="alter" var="alter"/>
<fmt:message bundle="${loc}" key="yes" var="yes"/>
<fmt:message bundle="${loc}" key="no" var="no"/>
<fmt:message bundle="${loc}" key="alter" var="alter"/>
<fmt:message bundle="${loc}" key="changeQuantity" var="changeQuantity"/>
<fmt:message bundle="${loc}" key="addToStorage" var="addToStorage"/>
<fmt:message bundle="${loc}" key="add" var="add"/>
<fmt:message bundle="${loc}" key="editRemedyInformation" var="editRemedyInformation"/>
<fmt:message bundle="${loc}" key="back" var="back"/>
<fmt:message bundle="${loc}" key="incorrectName" var="incorrectName"/>
<fmt:message bundle="${loc}" key="incorrectDescription" var="incorrectDescription"/>

<div class="container-fluid">
    <div class="container-fluid col-sm-8 mb-3 mt-5">
        <form class="border border-secondary pl-5 pr-5 pt-3 pb-3" action="/secure?command=update_remedy" method="post">
            <h3 class="text-center">${editRemedyInformation}</h3>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="remedyName">${remedyName}</label>
                    <input type="text" name="name" class="form-control form-control-sm" id="remedyName"
                           value="${storage.remedy.name}"
                           required>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="description">${description}</label>
                    <input type="text" class="form-control form-control-sm" id="description"
                           name="description" value="${storage.remedy.description}" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="receipt">${receipt}</label>
                    <select id="receipt" class="form-control form-control-sm" name="receiptRequired">
                        <option selected value="${storage.remedy.receiptRequired}">
                            <c:if test="${storage.remedy.receiptRequired == true}">
                                ${yes}
                            </c:if>
                            <c:if test="${storage.remedy.receiptRequired == false}">
                                ${no}
                            </c:if>
                        </option>
                        <option value="${!storage.remedy.receiptRequired}">
                            <c:if test="${!storage.remedy.receiptRequired == true}">
                                ${yes}
                            </c:if>
                            <c:if test="${!storage.remedy.receiptRequired == false}">
                                ${no}
                            </c:if>
                        </option>
                    </select>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="price">${price}</label>
                    <input type="number" name="price" min="0.00" max="9999.99" step="0.01"
                           class="form-control form-control-sm"
                           id="price" placeholder="0.00" value="${storage.remedy.price}">
                </div>
            </div>
            <button class="btn btn-sm btn-primary mr-2" type="submit">${alter}</button>
            <input type="hidden" name="id" value="${storage.remedy.id}">
            <c:if test="${storage.remedyLeft!=-1}">
                <button type="button" class="btn btn-primary btn-sm mr-2 ml-2" data-toggle="modal"
                        data-target="#changeQuantity">
                        ${changeQuantity}
                </button>
            </c:if>
            <c:if test="${storage.remedyLeft==-1}">
                <button type="button" class="btn btn-primary btn-sm mr-2 ml-2" data-toggle="modal"
                        data-target="#addToStorage">
                        ${addToStorage}
                </button>
            </c:if>
        </form>
        <!-- The Modal of change quantity-->
        <div class="modal fade" id="changeQuantity">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">${changeQuantity}</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="/secure?command=set_storage_quantity" method="post">
                            <div class="col-sm-6 mb-3 pl-0">
                                <label for="alterQuantity">${quantity}</label>
                                <input type="number" name="remedyLeft" min="0" step="1"
                                       class="form-control form-control-sm" id="alterQuantity"
                                       placeholder="0" value="${storage.remedyLeft}">
                            </div>
                            <button class="btn btn-sm btn-primary" type="submit">${alter}</button>
                            <input type="hidden" name="id" value="${storage.remedy.id}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="addToStorage">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">${addToStorage}</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="/secure?command=add_to_storage" method="post">
                            <div class="col-sm-6 mb-3 pl-0">
                                <label for="newQuantity">${quantity}</label>
                                <input type="number" name="remedyLeft" min="0" step="1"
                                       class="form-control form-control-sm"
                                       id="newQuantity" placeholder="0" value="0">
                            </div>
                            <button class="btn btn-sm btn-primary" type="submit">${add}</button>
                            <input type="hidden" name="id" value="${storage.remedy.id}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${param.incorrectName.equals('true')}">
        <p class="text-danger">
            <c:out value="${incorrectName}"/>
        </p>
    </c:if>
    <c:if test="${param.incorrectDescription.equals('true')}">
        <p class="text-danger">
            <c:out value="${incorrectDescription}"/>
        </p>
    </c:if>
</div>
<div class="d-flex container-fluid justify-content-start">
    <a href="/secure?command=go_to_remedy_administration">${back}</a>
</div>

<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
