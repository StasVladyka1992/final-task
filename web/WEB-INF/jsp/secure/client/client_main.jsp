<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 18.02.2019
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../../../../styles/user_main.css">
<%@ include file="../../constant_part/navbar.jsp" %>
<div class="container">
    <div class="d-flex flex-wrap justify-content-around mt-1">
        <div class="card mt-1 mb-1">
            <img class="card-img-top" src="../../../../images/remedy_searching.jpeg">
            <div class="card-body">
                <h5 class="card-title text-center">Поиск медицинских препаратов</h5>
                <p class="card-text">Поиск медицинских препаратов, добавление в корзину и покупка</p>
                <div class="d-flex justify-content-around">
                    <a href="/secure?command=go_to_remedy" type="button" class="btn btn-success">Далее</a>
                </div>
            </div>
        </div>
        <div class="card mt-1 mb-1">
            <img class="card-img-top" src="../../../../images/doctor_receipt.jpg">
            <div class="card-body">
                <h5 class="card-title text-center">Обращение за рецептом</h5>
                <p class="card-text">Обращение к врачу для выписки или продления электронного рецепта</p>
                <div class="d-flex justify-content-around">
                    <a href="#" type="button" class="btn btn-success">Далее</a>
                </div>
            </div>
        </div>
        <div class="card mt-1 mb-1">
            <img class="card-img-top" src="../../../../images/personal_data.jpeg">
            <div class="card-body">
                <h5 class="card-title text-center">Редактирование личных данных</h5>
                <p class="card-text">Редактирование личных учетных данных</p>
                <div class="d-flex justify-content-around">
                    <a href="#" type="button" class="btn btn-success ">Далее</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
