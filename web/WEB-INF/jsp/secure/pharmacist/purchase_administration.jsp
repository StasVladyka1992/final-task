<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 21.03.2019
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../../../../styles/user_main.css">
<%@ include file="../../constant_part/navbar.jsp" %>

<fmt:message bundle="${loc}" key="nothingFound" var="nothingFound"/>
<fmt:message bundle="${loc}" key="next" var="next"/>
<fmt:message bundle="${loc}" key="previous" var="previous"/>
<fmt:message bundle="${loc}" key="rejections" var="rejections"/>
<fmt:message bundle="${loc}" key="id" var="id"/>
<fmt:message bundle="${loc}" key="createdOn" var="createdOn"/>
<fmt:message bundle="${loc}" key="finishedOn" var="finishedOn"/>
<fmt:message bundle="${loc}" key="clientId" var="clientId"/>
<fmt:message bundle="${loc}" key="status" var="status"/>
<fmt:message bundle="${loc}" key="goToOrder" var="goToOrder"/>
<fmt:message bundle="${loc}" key="handledOrders" var="handledOrders"/>
<fmt:message bundle="${loc}" key="updateOrders" var="updateOrders"/>
<fmt:message bundle="${loc}" key="ordersAdministration" var="ordersAdministration"/>
<fmt:message bundle="${loc}" key="goToAuthorizedUserMain" var="goToAuthorizedUserMain"/>

<div class="container-fluid col-sm-10">
    <h4 class="mb-2">${ordersAdministration}</h4>
    <div class="table-responsive-md">
        <table class="table table-bordered">
            <thead>
            <th class="align-middle"><c:out value="${id}"/></th>
            <th class="align-middle"><c:out value="${createdOn}"/></th>
            <th class="align-middle"><c:out value="${status}"/></th>
            </thead>
            <c:if test="${clientOrderList.size()>0}">
                <c:forEach var="order" items="${clientOrderList}">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td>
                            <fmt:formatDate value="${order.createdOn}" pattern="yyyy-MM-dd hh:mm:ss"/>
                        </td>
                        <td>
                            <c:out value="${order.status}"/>
                        </td>
                        <td>
                            <div class="row justify-content-center">
                                <form class="mb-0" action="/secure?command=show_client_order" method="post">
                                    <button class="btn btn-sm btn-primary" type="submit">${goToOrder}</button>
                                    <input type="hidden"
                                           value="${order.id}" name="id">
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${clientOrderList.size()==0}">
                <tr>
                    <td colspan="6"><c:out value="${nothingFound}"/></td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
<div class="container-fluid d-flex justify-content-left">
    <div class="btn-group">
        <form action="/secure?command=show_handled_order_list" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${handledOrders}</button>
        </form>
    </div>
    <div class="btn-group">
        <form action="/secure?command=show_unhandled_order_list" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${updateOrders}</button>
        </form>
    </div>
</div>
<div class="d-flex container-fluid justify-content-start">
    <a href="/secure?command=go_to_authorized_user_main_page">${goToAuthorizedUserMain}</a>
</div>
