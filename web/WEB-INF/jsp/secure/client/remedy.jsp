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
<fmt:message bundle="${loc}" key="packing" var="packing"/>
<fmt:message bundle="${loc}" key="maker" var="maker"/>
<fmt:message bundle="${loc}" key="quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="price" var="price"/>
<fmt:message bundle="${loc}" key="receipt" var="receipt"/>
<fmt:message bundle="${loc}" key="next" var="next"/>
<fmt:message bundle="${loc}" key="previous" var="previous"/>
<fmt:message bundle="${loc}" key="nothingFound" var="nothingFound"/>
<fmt:message bundle="${loc}" key="search" var="search"/>
<fmt:message bundle="${loc}" key="yDefenition" var="yDefenition"/>
<fmt:message bundle="${loc}" key="searchingRemedyPlaceholder" var="searchingRemedyPlaceholder"/>
<fmt:message bundle="${loc}" key="remedySearching" var="remedySearching"/>
<fmt:message bundle="${loc}" key="buy" var="buy"/>
<fmt:message bundle="${loc}" key="addToBasket" var="addToBasket"/>
<fmt:message bundle="${loc}" key="appealForReceipt" var="appealForReceipt"/>
<fmt:message bundle="${loc}" key="showOnlyInStock" var="showOnlyInStock"/>

<div class="container-fluid">
    <h4>${remedySearching}</h4>
    <form action="/secure?command=find_remedy" method="post">
        <div class="form-row align-items-center">
            <div class="col-sm-5 my-1">
                <input type="text" class="form-control" name="remedyName" placeholder="${searchingRemedyPlaceholder}"
                       required>
            </div>
            <div class="col-auto my-1">
                <button type="submit" class="btn btn-primary">${search}</button>
            </div>
        </div>
        <div class="col-auto my-1">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="isExist" value="true" id="existence">
                <label class="form-check-label" for="existence">
                ${showOnlyInStock}
                </label>
            </div>
            <input type="hidden" name="currentPage" value="1"/>
        </div>
    </form>
</div>
<div class="table-responsive-md">
    <table class="table table-bordered">
        <thead>
        <th class="align-middle"><c:out value="${remedyName}"/></th>
        <th class="align-middle"><c:out value="${packing}"/></th>
        <th class="align-middle"><c:out value="${maker}"/></th>
        <th class="align-middle"><c:out value="${quantity}"/></th>
        <th class="align-middle"><c:out value="${price}"/></th>
        <th class="align-middle"><c:out value="${receipt}"/></th>
        </thead>
        <c:if test="${remedyList.size()!=0 || remedyList !=null}">
            <c:forEach var="remedy" items="${remedyList}">
                <tr>
                    <td><c:out value="${remedy.name}"/></td>
                    <td><c:out value="${remedy.packing}"/></td>
                    <td><c:out value="${remedy.maker}"/></td>
                    <td><c:out value="${remedy.quantity}"/></td>
                    <td><c:out value="${remedy.price}"/></td>
                    <td><c:out value="${remedy.receipt}"/></td>
                    <td><a href="#">${buy}</a>
                        <a href="" value="Купить">${addToBasket}</a>
                        <c:if test="${remedy.receipt == 'y'.charAt(0)}">
                            <a href="#">${appealForReceipt}</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${remedyList.size()==0}">
            <tr>
                <td colspan="6"><c:out value="${nothingFound}"/></td>
            </tr>
        </c:if>
    </table>
    <c:if test="${remedyList.size()!=0&& remedyList !=null}">
        <p><span id='star'>*</span>${yDefenition}</p>
    </c:if>
</div>
<c:if test="${sessionScope.pagesNumber>1}">
    <ul class="pagination justify-content-center">
        <!--available "Previous" link-->
        <c:if test="${currentPage>1}">
            <li class="page-item"><a class="page-link"
                                     href="/secure?command=find_remedy&currentPage=${currentPage-1}"><c:out
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
                                                    href="/secure?command=find_remedy&currentPage=${i}">${i}</a>
                    </li>
                </c:when>
                <c:when test="${i!=currentPage}">
                    <c:if test="${i>currentPage}">
                        <li class="page-item"><a class="page-link"
                                                 href="/secure?command=find_remedy&currentPage=${i}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${i<currentPage}">
                        <li class="page-item"><a class="page-link"
                                                 href="/secure?command=find_remedy&currentPage=${i}">${i}</a>
                        </li>
                    </c:if>
                </c:when>
            </c:choose>
        </c:forEach>
        <!--available "Next" link-->
        <c:if test="${currentPage+1<=pagesNumber}">
            <li class="page-item"><a class="page-link"
                                     href="/secure?command=find_remedy&currentPage=${currentPage+1}"><c:out
                    value="${next}"/></a>
            </li>
        </c:if>
        <!--disabled "Next" link-->
        <c:if test="${currentPage==pagesNumber}">
            <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${next}"/></a></li>
        </c:if>
    </ul>
</c:if>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>

