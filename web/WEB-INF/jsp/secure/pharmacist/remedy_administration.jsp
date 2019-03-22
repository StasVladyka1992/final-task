<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 03.03.2019
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>

<fmt:message bundle="${loc}" key="remedyName" var="firstName"/>
<fmt:message bundle="${loc}" key="id" var="id"/>
<fmt:message bundle="${loc}" key="price" var="price"/>
<fmt:message bundle="${loc}" key="receipt" var="receipt"/>
<fmt:message bundle="${loc}" key="description" var="description"/>
<fmt:message bundle="${loc}" key="quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="next" var="next"/>
<fmt:message bundle="${loc}" key="previous" var="previous"/>
<fmt:message bundle="${loc}" key="nothingFound" var="nothingFound"/>
<fmt:message bundle="${loc}" key="search" var="search"/>
<fmt:message bundle="${loc}" key="remedyAdministration" var="remedyAdministration"/>
<fmt:message bundle="${loc}" key="add" var="add"/>
<fmt:message bundle="${loc}" key="close" var="close"/>
<fmt:message bundle="${loc}" key="searchingRemedyPlaceholder" var="searchingRemedyPlaceholder"/>
<fmt:message bundle="${loc}" key="delete" var="delete"/>
<fmt:message bundle="${loc}" key="alter" var="alter"/>
<fmt:message bundle="${loc}" key="remedyAdding" var="remedyAdding"/>
<fmt:message bundle="${loc}" key="powder" var="powder"/>
<fmt:message bundle="${loc}" key="pill" var="pill"/>
<fmt:message bundle="${loc}" key="another" var="another"/>
<fmt:message bundle="${loc}" key="standard" var="standard"/>
<fmt:message bundle="${loc}" key="tube" var="tube"/>
<fmt:message bundle="${loc}" key="deletingUnsuccessfull" var="deletingUnsuccessfull"/>
<fmt:message bundle="${loc}" key="operationResultSuccess" var="oprerationResultSuccess"/>
<fmt:message bundle="${loc}" key="delete" var="delete"/>
<fmt:message bundle="${loc}" key="alter" var="alter"/>
<fmt:message bundle="${loc}" key="confirm" var="confirm"/>
<fmt:message bundle="${loc}" key="incorrectId" var="incorrectId"/>
<fmt:message bundle="${loc}" key="yes" var="yes"/>
<fmt:message bundle="${loc}" key="no" var="no"/>
<fmt:message bundle="${loc}" key="changeQuantity" var="changeQuantity"/>
<fmt:message bundle="${loc}" key="newRemedyAdding" var="newRemedyAdding"/>
<fmt:message bundle="${loc}" key="absenceInStorage" var="absenceInStorage"/>
<fmt:message bundle="${loc}" key="remedyNotExist" var="remedyNotExist"/>

<div class="container-fluid mb-2">
    <h5>${remedyAdministration}</h5>
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
</div>
<div class="container-fluid">
    <div class="table-responsive-md">
        <table class="table table-bordered">
            <thead>
            <th class="align-middle"><c:out value="${id}"/></th>
            <th class="align-middle"><c:out value="${firstName}"/></th>
            <th class="align-middle"><c:out value="${phone}"/></th>
            <th class="align-middle"><c:out value="${price}"/></th>
            <th class="align-middle"><c:out value="${receipt}"/></th>
            <th class="align-middle"><c:out value="${quantity}"/></th>
            </thead>
            <c:if test="${storageList.size()!=0 || storageList !=null}">
                <c:forEach var="storage" items="${storageList}">
                    <tr>
                        <td><c:out value="${storage.remedy.id}"/></td>
                        <td><c:out value="${storage.remedy.name}"/></td>
                        <td><c:out value="${storage.remedy.description}"/></td>
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
                                <form class="form-inline mr-1 ml-1" action="/secure?command=delete_remedy"
                                      method="post">
                                    <button type="submit" class="btn btn-sm btn-primary">${delete}</button>
                                    <input type="hidden" value="${storage.remedy.id}" name="id">
                                </form>
                                <form class="form-inline mr-1 ml-1" action="/secure?command=go_to_update_remedy"
                                      method="post">
                                    <button type="submit" class="btn btn-sm btn-primary">${alter}</button>
                                    <input type="hidden" value="${storage.remedy.id}" name="id">
                                </form>
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
    <%--TODO вставить другие информаторы по валидации данных--%>
    <c:if test="${param.incorrectId.equals('true')}">
        <p class="text-danger">
            <c:out value="${deletingUnsuccessfull}"/>
            <c:out value="${incorrectId}"/>
        </p>
    </c:if>
    <c:if test="${param.operationResult.equals('success')}">
        <p class="text-success">
            <c:out value="${oprerationResultSuccess}"/>
        </p>
    </c:if>
    <c:if test="${param.operationResult.equals('remedy not exist')}">
        <p class="text-success">
            <c:out value="${remedyNotExist}"/>
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
    <div class="container-fluid col-sm-8 mb-3 mt-5">
        <form class="border border-secondary pl-5 pr-5 pt-3 pb-3" action="/secure?command=add_remedy" method="post">
            <h5 class="text-center">${newRemedyAdding}</h5>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="remedyName">${firstName}</label>
                    <input type="text" name="name" class="form-control form-control-sm" id="remedyName"
                           required>
                    <%--<div class="valid-feedback">--%>
                    <%--Looks good!--%>
                    <%--</div>--%>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="description">${description}</label>
                    <input type="text" class="form-control form-control-sm" id="description"
                           name="description" required>
                    <%--<div class="valid-feedback">--%>
                    <%--Looks good!--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="receipt">${receipt}</label>
                    <select id="receipt" class="form-control form-control-sm" name="receiptRequired">
                        <option selected value="false">${no}</option>
                        <option value="true">${yes}</option>
                    </select>
                    <%--<div class="valid-feedback">--%>
                    <%--Please provide a valid state.--%>
                    <%--</div>--%>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="price">${price}</label>
                    <input type="number" name="price" min="0.00" max="9999.99" step="0.01"
                           class="form-control form-control-sm"
                           id="price" placeholder="0.00" required>
                    <%--<div class="invalid-feedback">--%>
                    <%--Please provide a valid zip.--%>
                    <%--</div>--%>
                </div>
            </div>
            <button class="btn btn-primary btn-sm" type="submit">${add}</button>
        </form>
    </div>
</div>
<div class="d-flex container-fluid justify-content-start">
    <a href="/secure?command=go_to_authorized_user_main_page">${goToAuthorizedUserMain}</a>
</div>
<div class="container-fluid" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
