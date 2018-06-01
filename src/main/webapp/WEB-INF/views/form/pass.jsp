<%--
  Created by IntelliJ IDEA.
  User: piotrdawidziuk
  Date: 03.05.18
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Passwords</title>
    <style>
        .error {
            color: red;
            background-color: yellow;
        }
    </style>
    <link rel="stylesheet" href="/css/stylesheet.css"></head>
<body>
<p class="center">
    Enter a sample password you want to test!
</p>

<form:form modelAttribute="password" method="post" class="center">
    <%--<form:errors path="*"/>--%>
    <p><label for="password_id">Enter password</label></p>
    <p><form:input path="value" id="password_id" type="password"/></p>
    <p><form:errors path="value" cssClass="error"/></p>

    <p>
        <button type="submit" class="button">Test password security!</button>
    </p>
</form:form>

<br>

<table class="messages">
    <tr>
        <th>Problems:</th>
    </tr>
    <c:forEach items="${messages}" var="message">
        <tr>
            <td>${message}</td>
        </tr>
    </c:forEach>
</table>
<br>

<table class="messages2">
    <tr>
        <th>Advantages:</th>
    </tr>
    <c:forEach items="${messages2}" var="message2">
        <tr>
            <td>${message2}</td>
        </tr>
    </c:forEach>
</table>
<p class="center"><a href="../fin/${user_id}">
    <button class="button">Continue</button>
</a></p>

</body>
</html>
