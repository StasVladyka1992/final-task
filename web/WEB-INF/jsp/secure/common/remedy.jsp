<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 26.02.2019
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="name" var="name"/>
<fmt:message bundle="${loc}" key="packing" var="packing"/>
<fmt:message bundle="${loc}" key="maker" var="maker"/>
<fmt:message bundle="${loc}" key="remainder" var="remainder"/>
<fmt:message bundle="${loc}" key="price" var="price"/>
<fmt:message bundle="${loc}" key="receipt" var="receipt"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid">
    <h4>Поиск медицинского препарата:</h4>
    <form action="/secure?command=find_remedy" method="post">
        <div class="form-row align-items-center">
            <div class="col-sm-5 my-1">
                <input type="text" class="form-control" name="name" placeholder="Введите название медпрепарата"
                       required>
            </div>
            <div class="col-auto my-1">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
        <div class="col-auto my-1">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="isExist" value="true" id="existence">
                <label class="form-check-label" for="existence">
                    Показывать только те, которые в наличии
                </label>
            </div>
        </div>
    </form>
</div>

<c:if test="${remedyList.size()!=0 || remedList !=null}">
    <div class="col-sm-10">
        <table class="table table-hover table-bordered">
            <thead>
            <th class="align-middle"><c:out value="${name}"/></th>
            <th class="align-middle"><c:out value="${packing}"/></th>
            <th class="align-middle"><c:out value="${maker}"/></th>
            <th class="align-middle"><c:out value="${remainder}"/></th>
            <th class="align-middle"><c:out value="${price}"/></th>
            </thead>
            <c:forEach var="remedy" items="${remedyList}">
                <tr>
                    <td><c:out value="${remedy.name}"/></td>
                    <td><c:out value="${remedy.packing}"/></td>
                    <td><c:out value="${remedy.maker}"/></td>
                    <td><c:out value="${remedy.remainder}"/></td>
                    <td><c:out value="${remedy.price}"/></td>
                    <td><a href="#">Купить</a> <a href="#" value="Купить">Добавить в корзину</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </div>
</c:if>
<c:if test="${pagesNumber!=1}">
    <ul class="pagination justify-content-center">
        <!--available "Previous" link-->
        <c:if test="${currentPage>1}">
            <li class="page-item"><a class="page-link"
                                     href="/secure?currentPage=${currentPage-1}&command=find_remedy"><c:out
                    value="${previousP}"/></a>
            </li>
        </c:if>
        <!--disabled "Previous" link-->
        <c:if test="${currentPage==1}">
            <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${previousP}"/></a></li>
        </c:if>
        <!--setting  query's parameters depending on clicked page-->
        <c:forEach begin="${1}" end="${pagesNumber}" var="i">
            <c:choose>
                <c:when test="${currentPage==i}">
                    <li class="page-item active"><a class="page-link"
                                                    href="/secure?currentPage=${i}&command=find_remedy">${i}</a>
                    </li>
                </c:when>
                <c:when test="${i!=currentPage}">
                    <c:if test="${i>currentPage}">
                        <li class="page-item"><a class="page-link"
                                                 href="/secure?currentPage=${i}&command=find_remedy">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${i<currentPage}">
                        <li class="page-item"><a class="page-link"
                                                 href="/secure?currentPage=${i}&command=find_remedy">${i}</a>
                        </li>
                    </c:if>
                </c:when>
            </c:choose>
        </c:forEach>
        <!--available "Next" link-->
        <c:if test="${currentPage+1<=pagesNumber}">
            <li class="page-item"><a class="page-link"
                                     href="/secure?currentPage=${currentPage+1}&command=find_remedy"><c:out
                    value="${nextP}"/></a>
            </li>
        </c:if>
        <!--disabled "Next" link-->
        <c:if test="${currentPage==pagesNumber}">
            <li class="page-item disabled"><a class="page-link " href="#"><c:out value="${nextP}"/></a></li>
        </c:if>
    </ul>
</c:if>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
</body>
</html>
