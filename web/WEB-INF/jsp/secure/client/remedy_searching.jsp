<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 26.02.2019
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="remedyName" var="remedyName"/>
<fmt:message bundle="${loc}" key="id" var="id"/>
<fmt:message bundle="${loc}" key="quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="description" var="lastName"/>
<fmt:message bundle="${loc}" key="price" var="price"/>
<fmt:message bundle="${loc}" key="receipt" var="receipt"/>
<fmt:message bundle="${loc}" key="next" var="next"/>
<fmt:message bundle="${loc}" key="previous" var="previous"/>
<fmt:message bundle="${loc}" key="nothingFound" var="nothingFound"/>
<fmt:message bundle="${loc}" key="search" var="search"/>
<fmt:message bundle="${loc}" key="yDefenition" var="yDefenition"/>
<fmt:message bundle="${loc}" key="searchingRemedyPlaceholder" var="searchingRemedyPlaceholder"/>
<fmt:message bundle="${loc}" key="remedySearching" var="remedySearching"/>
<fmt:message bundle="${loc}" key="placeOrder" var="placeOrder"/>
<fmt:message bundle="${loc}" key="goToAdding" var="goToAdding"/>
<fmt:message bundle="${loc}" key="askForReceipt" var="askForReceipt"/>
<fmt:message bundle="${loc}" key="showOnlyInStock" var="showOnlyInStock"/>
<fmt:message bundle="${loc}" key="applicationSent" var="applicationSent"/>
<fmt:message bundle="${loc}" key="receiptExist" var="receiptExist"/>
<fmt:message bundle="${loc}" key="absenceInStorage" var="absenceInStorage"/>
<fmt:message bundle="${loc}" key="quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="add" var="add"/>
<fmt:message bundle="${loc}" key="goodAdded" var="goodAdded"/>
<fmt:message bundle="${loc}" key="goToBasket" var="goToBasket"/>
<fmt:message bundle="${loc}" key="goodAlreadyInBasket" var="goodAlreadyInBasket"/>
<fmt:message bundle="${loc}" key="incorrectQuantity" var="incorrectQuantity"/>
<fmt:message bundle="${loc}" key="goToAuthorizedUserMain" var="goToAuthorizedUserMain"/>


<div class="container-fluid">
    <h4>${remedySearching}</h4>
    <form action="/secure?command=find_storage_position" method="post">
        <div class="form-row align-items-center">
            <div class="col-sm-5 my-1">
                <input type="text" class="form-control" name="name" placeholder="${searchingRemedyPlaceholder}"
                       required>
            </div>
            <div class="col-auto my-1">
                <button type="submit" class="btn btn-primary">${search}</button>
            </div>
        </div>
        <input type="hidden" name="currentPage" value="1"/>
    </form>
    <div class="table-responsive-md">
        <table class="table table-bordered">
            <thead>
            <th class="align-middle"><c:out value="${id}"/></th>
            <th class="align-middle"><c:out value="${remedyName}"/></th>
            <th class="align-middle"><c:out value="${price}"/></th>
            <th class="align-middle"><c:out value="${receipt}"/></th>
            <th class="align-middle"><c:out value="${quantity}"/></th>
            </thead>
            <c:if test="${storageList.size()!=0 || storageList !=null}">
                <c:forEach var="storage" items="${storageList}">
                    <tr>
                        <td><c:out value="${storage.remedy.id}"/></td>
                        <td><c:out value="${storage.remedy.name}"/></td>
                        <td><c:out value="${storage.remedy.price}"/></td>
                        <td><c:out value="${storage.remedy.receiptRequired}"/></td>
                        <td>
                            <c:if test="${storage.remedyLeft==-1}">
                                ${absenceInStorage}
                            </c:if>
                            <c:if test="${storage.remedyLeft!=-1}">
                                <c:out value="${storage.remedyLeft}"></c:out>
                            </c:if>
                        </td>
                        <td>
                            <div class="row justify-content-center">
                                <c:if test="${storage.remedyLeft !=-1 && storage.remedyLeft!=0}">
                                    <form action="/secure?command=show_remedy_info" method="post">
                                        <button type="submit" class="btn btn-sm btn-primary">${goToAdding}</button>
                                        <input type="hidden" value="${storage.remedy.id}" name="id">
                                    </form>
                                </c:if>
                                <c:if test="${storage.remedyLeft ==-1 || storage.remedyLeft==0}">
                                    <button type="submit" class="btn btn-sm btn-primary"
                                            disabled>${goToAdding}</button>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${storageList.size()==0}">
                <tr>
                    <td colspan="6"><c:out value="${nothingFound}"/></td>
                </tr>
            </c:if>
        </table>
    </div>
    <c:if test="${param.operationResult.equals('applicationAccepted')}">
        <p class="text-success">
            <c:out value="${applicationSent}"/>
        </p>
    </c:if>
    <c:if test="${param.receiptExist.equals('true')}">
        <p class="text-danger">
            <c:out value="${receiptExist}"/>
        </p>
    </c:if>
    <c:if test="${param.operationResult.equals('success')}">
        <p class="text-success">
            <c:out value="${goodAdded}"/>
            <c:out value="${sessionScope.get('orderDto').goods.size()}"/>
        </p>
    </c:if>
    <c:if test="${param.incorrectQuantity.equals('true')}">
        <p class="text-danger">
            <c:out value="${incorrectQuantity}"/>
        </p>
    </c:if>
    <c:if test="${param.operationResult.equals('goodAlreadyInBasket')}">
        <p class="text-danger">
            <c:out value="${goodAlreadyInBasket}"/>
        </p>
    </c:if>
    <c:if test="${sessionScope.pagesNumber>1}">
        <ul class="pagination justify-content-center">
            <!--available "Previous" link-->
            <c:if test="${currentPage>1}">
                <li class="page-item"><a class="page-link"
                                         href="/secure?command=find_storage_position&currentPage=${currentPage-1}"><c:out
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
                                                        href="/secure?command=find_storage_position&currentPage=${i}">${i}</a>
                        </li>
                    </c:when>
                    <c:when test="${i!=currentPage}">
                        <c:if test="${i>currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/secure?command=find_storage_position&currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${i<currentPage}">
                            <li class="page-item"><a class="page-link"
                                                     href="/secure?command=find_storage_position&currentPage=${i}">${i}</a>
                            </li>
                        </c:if>
                    </c:when>
                </c:choose>
            </c:forEach>
            <!--available "Next" link-->
            <c:if test="${currentPage+1<=pagesNumber}">
                <li class="page-item"><a class="page-link"
                                         href="/secure?command=find_storage_position&currentPage=${currentPage+1}"><c:out
                        value="${next}"/></a>
                </li>
            </c:if>
            <!--disabled "Next" link-->
            <c:if test="${currentPage==pagesNumber}">
                <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${next}"/></a></li>
            </c:if>
        </ul>
    </c:if>
</div
<div class="container-fluid">
    <div class="d-flex justify-content-between">
        <div class="col">
            <a href="/secure?command=go_to_authorized_user_main_page">${goToAuthorizedUserMain}</a>
        </div>
        <div class="col">
            <div class="d-flex justify-content-end">
                <c:if test="${sessionScope.get('orderDto').goods.size()!=0 && not empty sessionScope.get('orderDto').goods}">
                    <a href="/secure?command=go_to_basket">${goToBasket}</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>

