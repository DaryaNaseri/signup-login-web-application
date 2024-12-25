<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 12/25/2024
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forbidden</title>
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
<div class="wrapper">
    <div class="input-box-button-input">
        <button style="background-color: firebrick" class="button" onclick="location.href='signup.jsp'">Return</button>
    </div>
</div>
<%--#1abc9c--%>
<script>
   const message = '<%= request.getAttribute("errorMessage")%>'
   alert(message)
</script>
</body>
</html>
