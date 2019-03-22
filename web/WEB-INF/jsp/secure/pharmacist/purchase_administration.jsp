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
<fmt:message bundle="${loc}" key="canceledOrders" var="canceledOrders"/>
<fmt:message bundle="${loc}" key="paidOrders" var="paidOrders"/>
<fmt:message bundle="${loc}" key="updateOrders" var="updateOrders"/>
<fmt:message bundle="${loc}" key="ordersAdministration" var="ordersAdministration"/>

<div class="container-fluid col-sm-10">
    <h4 class="mb-2">${ordersAdministration}</h4>
    <div class="table-responsive-md">
        <table class="table table-bordered">
            <thead>
            <th class="align-middle"><c:out value="${id}"/></th>
            <th class="align-middle"><c:out value="${createdOn}"/></th>
            <th class="align-middle"><c:out value="${finishedOn}"/></th>
            <th class="align-middle"><c:out value="${clientId}"/></th>
            <th class="align-middle"><c:out value="${status}"/></th>
            </thead>
            <c:if test="${orderList.size()!=0 || orderList !=null}">
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.createdOn}"/></td>
                        <td><c:out value="${order.finishedOn}"/></td>
                        <td><c:out value="${order.clientId}"/></td>
                        <td><c:out value="${order.status}"/></td>
                        <td>
                            <div class="row justify-content-center">
                                <form action="/secure?command=reject_application" method="post">
                                    <button class="btn btn-primary" type="submit">${goToOrder}</button>
                                    <input type="hidden"
                                           value="${order.id}" name="id">
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${receiptList.size()==0}">
                <tr>
                    <td colspan="6"><c:out value="${nothingFound}"/></td>
                </tr>
            </c:if>
        </table>
    </div>

    <c:if test="${param.operationResult.equals('prescriptionWritten')}">
        <p class="text-success">
                ${prescriptionWrote}
        </p>
    </c:if>

    <c:if test="${sessionScope.pagesNumber>1}">
        <ul class="pagination justify-content-center">
            <!--available "Previous" link-->
            <c:if test="${currentPage>1}">
                <li class="page-item"><a class="page-link"
                                         href="/secure?command=show_unhandled_applications&currentPage=${currentPage-1}"><c:out
                        value="${previous}"/></a>
                </li>
            </c:if>
            <!--disabled "Previous" link-->
            <c:if test="${currentPage==1}">
                <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${previous}"/></a></li>
            </c:if>
            <!--setting  query's parameters depending on clicked page-->
            <c:forEach begin="${1}" end="${pagesNumber}" var="i">
                <c:choose>
                    <c:when test="${currentPage==i}">
                        <li class="page-item active"><a class="page-link"
                                                        href="/secure?command=show_unhandled_applications&currentPage=${i}">${i}</a>
                        </li>
                    </c:when>
                    <c:when test="${i!=currentPage}">
                        <c:if test="${i>currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/secure?command=show_unhandled_applications&currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${i<currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/secure?command=show_unhandled_applications&currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                    </c:when>
                </c:choose>
            </c:forEach>
            <!--available "Next" link-->
            <c:if test="${currentPage+1<=pagesNumber}">
                <li class="page-item"><a class="page-link"
                                         href="/secure?command=show_unhandled_applications&currentPage=${currentPage+1}"><c:out
                        value="${next}"/></a>
                </li>
            </c:if>
            <!--disabled "Next" link-->
            <c:if test="${currentPage==pagesNumber}">
                <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${next}"/></a></li>
            </c:if>
        </ul>
    </c:if>
</div>
<div class="container-fluid">
    <div class="btn-group">
        <form action="#" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${canceledOrders}</button>
        </form>
    </div>
    <div class="btn-group">
        <form action="#" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${paidOrders}</button>
        </form>
    </div>
    <div class="btn-group">
        <form action="#" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${updateOrders}</button>
        </form>
    </div>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
