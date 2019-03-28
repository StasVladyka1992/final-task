<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 20.03.2019
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="remedyName" var="remedyName"/>
<fmt:message bundle="${loc}" key="id" var="id"/>
<fmt:message bundle="${loc}" key="quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="availableOnStorage" var="availableOnStorage"/>
<fmt:message bundle="${loc}" key="description" var="description"/>
<fmt:message bundle="${loc}" key="price" var="price"/>
<fmt:message bundle="${loc}" key="receipt" var="receipt"/>
<fmt:message bundle="${loc}" key="addToBasket" var="addToBasket"/>
<fmt:message bundle="${loc}" key="askForReceipt" var="askForReceipt"/>
<fmt:message bundle="${loc}" key="add" var="add"/>
<fmt:message bundle="${loc}" key="toRemedySearching" var="toRemedySearching"/>


<h4 class="mt-3 mb-3 pl-3">${description} ${storage.remedy.name}</h4>
<div class="table-responsive-md col-sm-6">
    <table class="table table-bordered">
        <tr>
            <td class="align-middle"><c:out value="${id}"/></td>
            <td><c:out value="${storage.remedy.id}"/></td>
        </tr>
        <tr>
            <td class="align-middle"><c:out value="${remedyName}"/></td>
            <td><c:out value="${storage.remedy.name}"/></td>
        </tr>
        <tr>
            <td class="align-middle"><c:out value="${price}"/></td>
            <td><c:out value="${storage.remedy.price}"/></td>
        </tr>
        <tr>
            <td class="align-middle"><c:out value="${receipt}"/></td>
            <td><c:out value="${storage.remedy.receiptRequired}"/></td>
        </tr>
        <tr>
            <td class="align-middle"><c:out value="${availableOnStorage}"/></td>
            <td>
                <c:if test="${storage.remedyLeft==-1}">
                    ${absenceInStorage}
                </c:if>
                <c:if test="${storage.remedyLeft!=-1}">
                    <c:out value="${storage.remedyLeft}"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="align-middle"><c:out value="${description}"/></td>
            <td><c:out value="${storage.remedy.description}"/></td>
        </tr>
    </table>
</div>
<div class="container-fluid">
    <c:if test="${storage.remedyLeft ==-1 || storage.remedyLeft==0}">
        <button type="submit" class="btn btn-sm btn-primary"
                disabled>${addToBasket}</button>
    </c:if>
    <form class="form-row pl-1" action="/secure?command=ask_for_receipt" method="post">
        <c:if test="${storage.remedy.receiptRequired}">
            <button type="submit"
                    class="btn btn-sm btn-primary mr-2">${askForReceipt}</button>
        </c:if>
        <input type="hidden" value="${storage.remedy.id}" name="id">
        <button type="button" class="btn btn-sm btn-primary mr-2" data-toggle="modal"
                data-target="#addToBasket">${addToBasket}
        </button>
    </form>
</div>
<div class="d-flex container-fluid justify-content-start mt-3">
    <a href="/secure?command=go_to_client_searching_page">${toRemedySearching}</a>
</div>


<div class="modal fade" id="addToBasket">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">${quantity}</h4>
                <button type="button" class="close" data-dismiss="modal">&times;
                </button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <form action="/secure?command=add_to_basket" method="post">
                    <div class="col-sm-6 pl-0">
                        <label for="alterQuantity">${quantity}</label>
                        <input type="number" name="quantity" min="1" step="1"
                               max="${storage.remedyLeft}"
                               class="form-control form-control-sm"
                               id="alterQuantity" value="1"
                               placeholder="0">
                    </div>
                    <button class="btn btn-sm btn-primary mt-2"
                            type="submit">${add}</button>
                    <input type="hidden" name="id"
                           value="${storage.id}">
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>

