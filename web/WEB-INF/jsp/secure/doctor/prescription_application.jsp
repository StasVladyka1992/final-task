<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 15.03.2019
  Time: 15:55
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
<fmt:message bundle="${loc}" key="writePrescription" var="writePrescription"/>
<fmt:message bundle="${loc}" key="reject" var="reject"/>
<fmt:message bundle="${loc}" key="prescriptionWriting" var="prescriptionWriting"/>
<fmt:message bundle="${loc}" key="nothingFound" var="nothingFound"/>
<fmt:message bundle="${loc}" key="next" var="next"/>
<fmt:message bundle="${loc}" key="previous" var="previous"/>
<fmt:message bundle="${loc}" key="write" var="write"/>
<fmt:message bundle="${loc}" key="expireDate" var="expireDate"/>
<fmt:message bundle="${loc}" key="receiptComment" var="receiptComment"/>
<fmt:message bundle="${loc}" key="receiptData" var="receiptData"/>
<fmt:message bundle="${loc}" key="addComment" var="addComment"/>
<fmt:message bundle="${loc}" key="rejectComment" var="rejectComment"/>
<fmt:message bundle="${loc}" key="prescriptionWrote" var="prescriptionWrote"/>
<fmt:message bundle="${loc}" key="applicationRejected" var="applicationRejected"/>
<fmt:message bundle="${loc}" key="rejections" var="rejections"/>
<fmt:message bundle="${loc}" key="approvals" var="approvals"/>
<fmt:message bundle="${loc}" key="remedy" var="remedy"/>
<fmt:message bundle="${loc}" key="checkApplications" var="checkApplications"/>
<fmt:message bundle="${loc}" key="goToAuthorizedUserMain" var="goToAuthorizedUserMain"/>

<div class="container-fluid ">
    <h4 class="mb-2">${prescriptionWriting}</h4>
    <div class="table-responsive-md">
        <table class="table table-bordered">
            <thead>
            <th class="align-middle"><c:out value="${id}"/></th>
            <th class="align-middle"><c:out value="${remedy}"/></th>
            <th class="align-middle"><c:out value="${status}"/></th>
            <th class="align-middle"><c:out value="${firstName}"/></th>
            <th class="align-middle"><c:out value="${lastName}"/></th>
            <th class="align-middle"><c:out value="${email}"/></th>
            </thead>
            <c:if test="${receiptList.size()!=0 || receiptList !=null}">
                <c:forEach var="receipt" items="${receiptList}">
                    <tr>
                        <td><c:out value="${receipt.id}"/></td>
                        <td><c:out value="${receipt.remedy.name}"/></td>
                        <td><c:out value="${receipt.status}"/></td>
                        <td><c:out value="${receipt.client.firstName}"/></td>
                        <td><c:out value="${receipt.client.lastName}"/></td>
                        <td><c:out value="${receipt.client.email}"/></td>
                        <td>
                            <div class="row justify-content-center">
                                <button type="button" class="btn btn-primary btn-sm mr-2 ml-2" data-toggle="modal"
                                        data-target="#writePrescription">
                                        ${writePrescription}
                                </button>
                                <button type="button" class="btn btn-primary btn-sm mr-2 ml-2" data-toggle="modal"
                                        data-target="#reject">
                                        ${reject}
                                </button>
                                <div class="modal fade" id="writePrescription">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <!-- Modal Header -->
                                            <div class="modal-header">
                                                <h4 class="modal-title mb-2">${receiptData}</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                            </div>
                                            <!-- Modal body -->
                                            <div class="modal-body">
                                                <form action="/secure?command=write_prescription" method="post">
                                                    <div class="form-group pl-0 col-sm-8 mb-3">
                                                        <div class="form-group">
                                                            <label for="expireDate">${expireDate}</label>
                                                            <jsp:useBean id="date" class="java.util.Date"/>
                                                            <input class="form-control" type="date" name="expireDate"
                                                                   value="<fmt:formatDate value="${date}"  pattern="yyyy-MM-dd"/>"
                                                                   min="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>"
                                                                   id="expireDate">
                                                        </div>
                                                    </div>
                                                    <div class="form-group pl-0 col-sm-12 mb-3">
                                                        <label for="approveComment">${receiptComment}</label>
                                                        <textarea class="form-control" rows="5" id="approveComment"
                                                                  name="message"
                                                                  placeholder="${addComment}" required></textarea>
                                                    </div>
                                                    <button class="btn btn-primary" type="submit">${write}</button>
                                                    <input type="hidden"
                                                           value="${sessionScope.user.id}" name="doctorId">
                                                    <input type="hidden" value="${receipt.id}" name="id">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal fade" id="reject">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <!-- Modal Header -->
                                            <div class="modal-header">
                                                <h4 class="modal-title">${receiptComment}</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                            </div>
                                            <!-- Modal body -->
                                            <div class="modal-body">
                                                <form action="/secure?command=reject_application" method="post">
                                                    <div class="form-group pl-0 col-sm-12 mb-3">
                                                        <label for="rejectComment">${receiptComment}</label>
                                                        <textarea class="form-control" rows="5" id="rejectComment"
                                                                  name="message" required
                                                                  placeholder="${addComment}"></textarea>
                                                    </div>
                                                    <button class="btn btn-primary" type="submit">${reject}</button>
                                                    <input type="hidden"
                                                           value="${sessionScope.user.id}" name="doctorId">
                                                    <input type="hidden" value="${receipt.id}" name="id">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
    <c:if test="${param.operationResult.equals('applicationRejected')}">
        <p class="text-success">
                ${applicationRejected}
        </p>
    </c:if>
    <%--Pagination--%>
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
        <form action="/secure?command=show_rejected_applications" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${rejections}</button>
        </form>
    </div>
    <div class="btn-group">
        <form action="/secure?command=show_written_prescriptions" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${approvals}</button>
        </form>
    </div>
    <div class="btn-group">
        <form action="/secure?command=show_unhandled_applications" method="post" class="form-inline mr-1">
            <button type="submit" class="btn btn-sm btn-primary">${checkApplications}</button>
        </form>
    </div>
</div>
<div class="d-flex container-fluid justify-content-start">
    <a href="/secure?command=go_to_authorized_user_main_page">${goToAuthorizedUserMain}</a>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>