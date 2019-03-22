<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 19.03.2019
  Time: 18:20
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
<fmt:message bundle="${loc}" key="basket" var="basket"/>
<fmt:message bundle="${loc}" key="cleanBasket" var="cleanBasket"/>
<fmt:message bundle="${loc}" key="deleteFromBasket" var="deleteFromBasket"/>
<fmt:message bundle="${loc}" key="placeOrder" var="placeOrder"/>
<fmt:message bundle="${loc}" key="changeQuantity" var="changeQuantity"/>
<fmt:message bundle="${loc}" key="remedyDeleted" var="remedyDeleted"/>
<fmt:message bundle="${loc}" key="goToRemedySearching" var="goToRemedySearching"/>
<fmt:message bundle="${loc}" key="noGoodsInBasket" var="noGoodsInBasket"/>
<fmt:message bundle="${loc}" key="quantityChanged" var="quantityChanged"/>
<fmt:message bundle="${loc}" key="basketCleaned" var="basketCleaned"/>
<fmt:message bundle="${loc}" key="incorrectQuantity" var="incorrectQuantity"/>

<h4 class="mt-3 mb-3 pl-3">${basket}</h4>
<div class="table-responsive-md col-sm-6">
    <c:forEach items="${sessionScope.get('orderDto').goods}" var="entry">
        <c:set var="storage" value="${entry.key}"/>
        <c:set var="quantityToBuy" value="${entry.value}"/>
        <table class="table table-bordered mb-2">
            <tr>
                <td class="align-middle w-25"><c:out value="${remedyName}"/></td>
                <td><c:out value="${storage.remedy.name}"/></td>
            </tr>
            <tr>
                <td class="align-middle w-25"><c:out value="${price}"/></td>
                <td><c:out value="${storage.remedy.price}"/></td>
            </tr>
            <tr>
                <td class="align-middle w-25"><c:out value="${receipt}"/></td>
                <td><c:out value="${storage.remedy.receiptRequired}"/></td>
            </tr>
            <tr>
                <td class="align-middle w-25"><c:out value="${quantity}"/></td>
                <td>
                    <form id="changeQuantity" class="pl-1 d-inline-block" method="post"
                          action="/secure?command=change_purchasing_quantity">
                        <input type="number" name="quantity" min="1" step="1"
                               max="${storage.remedyLeft}" class="form-control form-control-sm mt-1"
                               value="${quantityToBuy}">
                        <input type="hidden" value="${storage.id}" name="id">
                    </form>
                </td>
            </tr>
            <tr>
                <td class="align-middle w-25"><c:out value="${description}"/></td>
                <td><c:out value="${storage.remedy.description}"/></td>
            </tr>
            <tr>
                <td colspan="2" class="pl-0 pr-0">
                    <div class="container-fluid pl-0">
                        <form class="pl-1 d-inline-block" action="/secure?command=delete_from_basket" method="post">
                            <button type="submit"
                                    class="btn btn-sm btn-primary mr-2 mb-1 mt-1">${deleteFromBasket}</button>
                            <input type="hidden" value="${storage.id}" name="id">
                        </form>
                        <button form="changeQuantity" type="submit" class="btn btn-sm btn-primary mr-2 mb-1 mt-1">
                                ${changeQuantity}</button>
                        <c:if test="${storage.remedy.receiptRequired}">
                            <form class="pl-1 d-inline-block" action="/secure?command=ask_for_receipt" method="post">
                                <button type="submit"
                                        class="btn btn-sm btn-primary mr-2 mb-1 mt-1">${askForReceipt}</button>
                                <input type="hidden" value="${storage.id}" name="id">
                            </form>
                        </c:if>
                    </div>
                </td>
            </tr>
        </table>
    </c:forEach>
</div>
<!--Кнопки-->
<div class="container-fluid mb-2">
    <c:if test="${sessionScope.get('orderDto').goods.size()!=0 || empty sessionScope.get('orderDto')}">
        <a href="/secure?command=buy" type="button" class="btn btn-sm btn-success mr-2">${placeOrder}</a>
        <a href="/secure?command=go_to_client_searching_page" type="button"
           class="btn btn-sm btn-success mr-2">${addToBasket}</a>
        <a href="/secure?command=clean_basket" type="button" class="btn btn-sm btn-danger">${cleanBasket}</a>
    </c:if>
    <c:if test="${param.operationResult.equals('remedy_deleted')}">
        <p class="text-success">
                ${remedyDeleted}
        </p>
    </c:if>
    <c:if test="${param.operationResult.equals('quantity_changed')}">
        <p class="text-success">
                ${quantityChanged}
        </p>
    </c:if>
    <c:if test="${param.operationResult.equals('basket_cleaned')}">
        <p class="text-success">
                ${basketCleaned}
        </p>
    </c:if>
    <c:if test="${param.incorrectQuantity.equals('true')}">
        <p class="text-danger">
            <c:out value="${incorrectQuantity}"/>
        </p>
    </c:if>
    <c:if test="${sessionScope.get('orderDto').goods.size()==0}">
        <p>${noGoodsInBasket}</p>
        <a href="/secure?command=go_to_client_searching_page" class="mr-2">${goToRemedySearching}</a>
    </c:if>
</div>
<%--<div class="container-fluid fixed-bottom" id="footer">--%>
<%--<%@ include file="../../constant_part/footer.jsp" %>--%>
<%--</div>--%>
