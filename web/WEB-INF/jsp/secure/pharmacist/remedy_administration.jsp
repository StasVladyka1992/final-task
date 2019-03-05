<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 03.03.2019
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>

<fmt:message bundle="${loc}" key="name" var="name"/>
<fmt:message bundle="${loc}" key="idRemedy" var="idRemedy"/>
<fmt:message bundle="${loc}" key="packing" var="packing"/>
<fmt:message bundle="${loc}" key="maker" var="maker"/>
<fmt:message bundle="${loc}" key="quantity" var="quantity"/>
<fmt:message bundle="${loc}" key="price" var="price"/>
<fmt:message bundle="${loc}" key="receipt" var="receipt"/>
<fmt:message bundle="${loc}" key="next" var="next"/>
<fmt:message bundle="${loc}" key="previous" var="previous"/>
<fmt:message bundle="${loc}" key="nothingFound" var="nothingFound"/>
<fmt:message bundle="${loc}" key="search" var="search"/>
<fmt:message bundle="${loc}" key="remedyAdministration" var="remedyAdministration"/>
<fmt:message bundle="${loc}" key="add" var="add"/>
<fmt:message bundle="${loc}" key="close" var="close"/>
<fmt:message bundle="${loc}" key="searchingRemedyPlaceholder" var="searchingRemedyPlaceholder"/>
<fmt:message bundle="${loc}" key="delete" var="delete"/>
<fmt:message bundle="${loc}" key="update" var="update"/>
<fmt:message bundle="${loc}" key="remedyAdding" var="remedyAdding"/>
<fmt:message bundle="${loc}" key="yes" var="yes"/>
<fmt:message bundle="${loc}" key="no" var="no"/>

<div class="container-fluid">
    <h5>${remedyAdministration}</h5>
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
        <input type="hidden" name="currentPage" value="1"/>
    </form>
</div>
<div class="table-responsive-md col-sm-10">
    <table class="table col-sm-10 table-bordered">
        <thead>
        <th class="aligh-miide"><c:out value="${idRemedy}"/></th>
        <th class="align-middle"><c:out value="${name}"/></th>
        <th class="align-middle"><c:out value="${packing}"/></th>
        <th class="align-middle"><c:out value="${maker}"/></th>
        <th class="align-middle"><c:out value="${quantity}"/></th>
        <th class="align-middle"><c:out value="${price}"/></th>
        <th class="align-middle"><c:out value="${receipt}"/></th>
        </thead>
        <c:if test="${remedyList.size()!=0 || remedyList !=null}">
            <c:forEach var="remedy" items="${remedyList}">
                <tr>
                    <td><c:out value="${remedy.idRemedy}"/></td>
                    <td><c:out value="${remedy.name}"/></td>
                    <td><c:out value="${remedy.packing}"/></td>
                    <td><c:out value="${remedy.maker}"/></td>
                    <td><c:out value="${remedy.quantity}"/></td>
                    <td><c:out value="${remedy.price}"/></td>
                    <td><c:out value="${remedy.receipt}"/></td>
                    <td><a href="#">${delete}</a>
                        <a href="#">${update}</a>
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
<div class="container-fluid">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">${add}</button>
    <!-- The Modal -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">${remedyAdding}</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body">
                    <form action="/action_page.php">
                        <div class="form-group">
                            <label for="name">${name}</label>
                            <input type="text" class="form-control" id="name">
                        </div>
                        <div class="form-group">
                            <label for="packing">${packing}</label>
                            <input type="text" class="form-control" id="packing">
                        </div>
                        <div class="form-group">
                            <label for="maker">${maker}</label>
                            <input type="text" class="form-control" id="maker">
                        </div>
                        <div class="form-group">
                            <label for="quantity">${quantity}</label>
                            <input type="number" step="1" min=0 class="form-control" id="quantity">
                        </div>
                        <div class="form-group">
                            <label for="price">${price}</label>
                            <input type="number" min="0.00" step="0.01" class="form-control" id="price">
                        </div>
                        <div class="form-group">
                            <label for="receipt">${receipt}</label>
                            <select id="receipt" class="custom-select">
                                <option selected value="n">${no}</option>
                                <option value="y">${yes}</option>
                            </select>
                        </div>
                    </form>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">${add}</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">${close}</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
