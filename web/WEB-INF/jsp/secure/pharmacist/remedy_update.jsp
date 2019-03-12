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
<fmt:message bundle="${loc}" key="setQuantity" var="setQuantity"/>
<div class="container-fluid">

    <div class="container-fluid col-sm-8 mb-3 mt-5">
        <form class="border border-secondary pl-5 pr-5 pt-3 pb-3" action="/secure?command=update_remedy" method="post">
            <h3 class="text-center">Редактирование информации о медпрепарате</h3>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="remedyName">${remedyName}</label>
                    <input type="text" name="name" class="form-control form-control-sm" id="remedyName"
                           value="${remedy.name}"
                           required>
                    <%--<div class="valid-feedback">--%>
                    <%--Looks good!--%>
                    <%--</div>--%>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="description">${description}</label>
                    <input type="text" class="form-control form-control-sm" id="description"
                           name="description" value="${remedy.description}" required>
                    <%--<div class="valid-feedback">--%>
                    <%--Looks good!--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="receipt">${receipt}</label>
                    <select id="receipt" class="form-control form-control-sm" name="receiptRequired">
                        <option selected value="${remedy.receiptRequired}">
                            <c:if test="${remedy.receiptRequired == true}">
                                ${yes}
                            </c:if>
                            <c:if test="${remedy.receiptRequired == false}">
                                ${no}
                            </c:if>
                        </option>
                        <option value="${!remedy.receiptRequired}">
                            <c:if test="${!remedy.receiptRequired == true}">
                                ${yes}
                            </c:if>
                            <c:if test="${!remedy.receiptRequired == false}">
                                ${no}
                            </c:if>
                        </option>
                    </select>
                    <%--<div class="valid-feedback">--%>
                    <%--Please provide a valid state.--%>
                    <%--</div>--%>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="price">${price}</label>
                    <input type="number" name="price" min="0.00" max="9999.99" step="0.01"
                           class="form-control form-control-sm"
                           id="price" placeholder="0.00" value="${remedy.price}">
                    <%--<div class="invalid-feedback">--%>
                    <%--Please provide a valid zip.--%>
                    <%--</div>--%>
                </div>
            </div>
            <button class="btn btn-sm btn-primary mr-2 ml-2" type="submit">${alter}</button>
            <input type="hidden" name="id" value="${remedy.id}">
            <form class="form-inline" action="/secure?command=go_to_storage_quantity" method="post">
                <button class="btn btn-sm btn-primary mr-2 ml-2" type="submit">${setQuantity}</button>
                <input type="hidden" name="remedyId" value="${remedy.id}">
            </form>
        </form>
    </div>
    <a href="/secure?command=go_to_remedy_administration">Вернуться на страницу поиска медпрепаратов</a>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
