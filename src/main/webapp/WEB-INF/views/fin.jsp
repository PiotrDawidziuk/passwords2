<%--
  Created by IntelliJ IDEA.
  User: piotrdawidziuk
  Date: 08.05.18
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="/css/stylesheet.css">
</head>
<body>
<table class="list">
    <tr>
        <th>Your passwords:</th>

    </tr>
    <c:forEach items="${passwords}" var="password">
        <tr>
            <td>${password.value}</td>
        </tr>
    </c:forEach>
</table>

<p>Your e-mail, login and all your passwords were saved to the database.</p>
<p>If you did that on another website, someone could use them to break into your e-mail or Facebook!</p>

<p class="messages">Remember: Never show your passwords to anyone!</p>

<p><a href="/" class="button">Try again!</a>
<a href="../delete/${user_id}" class="button">Delete my data</a>
</p>


</body>
</html>
