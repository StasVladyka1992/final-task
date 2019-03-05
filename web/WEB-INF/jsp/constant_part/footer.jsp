<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 24.02.2019
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:message bundle="${loc}" key="contactInfo" var="contactInfo"/>
<fmt:message bundle="${loc}" key="location" var="location"/>
<fmt:message bundle="${loc}" key="rights" var="rights"/>
<fmt:message bundle="${loc}" key="socialNetworks" var="socialNetworks"/>
<fmt:message bundle="${loc}" key="email" var="email"/>
<fmt:message bundle="${loc}" key="address" var="address"/>


<div class="container-fluid" id="footer">
    <div class="row">
        <div class="col-7">
            <p class="text-white footer-text constText">${contactInfo}</p>
            <p class="text-white footer-text constText small">${address} ${location}</p>
            <p class="text-white footer-text constText small">${email}: ${initParam['adminMail']}</p>
        </div>
        <div class="col-5">
            <p class="text-right constText">${socialNetworks}</p>
            <ul id="socialLinks" class="menu text-right">
                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                <li><a href="#"><i class="fa fa-youtube-play"></i></a></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-12 text-center">
            <p class="text-white constText small" id="rights">${rights}</p>
        </div>
    </div>
</div>





