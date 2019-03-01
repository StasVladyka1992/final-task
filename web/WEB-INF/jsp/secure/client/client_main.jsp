<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 18.02.2019
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <a href="#remedy" data-toggle="collapse">Collapsible</a>
            <div class="collapse" id="remedy">
                <p>Here you can order a remedy or you can check it's existance. <a href="/secure?command=go_to_remedy">Find remedy</a></p>
            </div>
        </div>
        <div class="col-sm-2">
            <a href="#receipt" data-toggle="collapse">Collapsible</a>
            <div class="collapse" id="receipt">
                <p>You can ask your doctor from here for remedy receipt. <a href="">Ask for receipt</a></p>
            </div>
        </div>
    </div>
</div>

<a href="/index?command=command">empty command</a>
<div class="container-fluid fixed-bottom" id="footer">
<%@ include file="../../constant_part/footer.jsp"%>
</div>
</body>
</html>
