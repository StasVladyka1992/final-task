<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 18.03.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../../../../styles/user_main.css">
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="id" var="id"/>
<fmt:message bundle="${loc}" key="status" var="status"/>
<fmt:message bundle="${loc}" key="firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="email" var="email"/>
<fmt:message bundle="${loc}" key="remedyName" var="firstName"/>
<fmt:message bundle="${loc}" key="nothingFound" var="nothingFound"/>
<fmt:message bundle="${loc}" key="next" var="next"/>
<fmt:message bundle="${loc}" key="previous" var="previous"/>
<fmt:message bundle="${loc}" key="expireDate" var="expireDate"/>
<fmt:message bundle="${loc}" key="receiptComment" var="receiptComment"/>
<fmt:message bundle="${loc}" key="back" var="back"/>
<fmt:message bundle="${loc}" key="receiptRejections" var="receiptRejections"/>
<fmt:message bundle="${loc}" key="rejectionDate" var="rejectionDate"/>

<div class="container-fluid ">
    <h4 class="mb-2">${receiptRejections}</h4>
    <div class="table-responsive-md">
        <table class="table table-bordered">
            <thead>
            <th class="align-middle"><c:out value="${id}"/></th>
            <th class="align-middle"><c:out value="${firstName}"/></th>
            <th class="align-middle"><c:out value="${status}"/></th>
            <th class="align-middle"><c:out value="${rejectionDate}"/></th>
            <th class="align-middle"><c:out value="${firstName}"/></th>
            <th class="align-middle"><c:out value="${lastName}"/></th>
            <th class="align-middle"><c:out value="${email}"/></th>
            <th class="align-middle"><c:out value="${receiptComment}"/></th>
            </thead>
            <c:if test="${receiptList.size()!=0 || receiptList !=null}">
                <c:forEach var="receipt" items="${receiptList}">
                    <tr>
                        <td><c:out value="${receipt.id}"/></td>
                        <td><c:out value="${receipt.remedy.name}"/></td>
                        <td><c:out value="${receipt.status}"/></td>
                        <td>
                            <fmt:formatDate value="${receipt.prescriptionDate}" pattern="yyyy-MM-dd"/>
                        </td>
                        <td><c:out value="${receipt.client.firstName}"/></td>
                        <td><c:out value="${receipt.client.lastName}"/></td>
                        <td><c:out value="${receipt.client.email}"/></td>
                        <td><c:out value="${receipt.message}"/></td>
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
    <c:if test="${sessionScope.pagesNumber>1}">
        <ul class="pagination justify-content-center">
            <!--available "Previous" link-->
            <c:if test="${currentPage>1}">
                <li class="page-item"><a class="page-link"
                                         href="/secure?command=show_rejected_applications&currentPage=${currentPage-1}"><c:out
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
                                                        href="/secure?command=show_rejected_applications&currentPage=${i}">${i}</a>
                        </li>
                    </c:when>
                    <c:when test="${i!=currentPage}">
                        <c:if test="${i>currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/secure?command=show_rejected_applications&currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${i<currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/secure?command=show_rejected_applications&currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                    </c:when>
                </c:choose>
            </c:forEach>
            <!--available "Next" link-->
            <c:if test="${currentPage+1<=pagesNumber}">
                <li class="page-item"><a class="page-link"
                                         href="/secure?command=show_rejected_applications&currentPage=${currentPage+1}"><c:out
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
        <a href="/secure?command=show_unhandled_applications" type="button" class="btn btn-sm btn-primary">${back}</a>
    </div>
</div>

<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
