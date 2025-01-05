<%@ page import="ir.maktabsharif.usersignuploginapplication.model.User" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.Permission" %><%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 12/24/2024
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%--<form action="signup.jsp"></form>--%>
<div class="container">
    <ol>
<%--        <% User user = (User) session.getAttribute("user");%>--%>
<%--        <%if(user.getUserRole().getPermissionList().contains(Permission.USER_CRUD_OPERATION)&&--%>
<%--            user.getPermissions().contains(Permission.USER_CRUD_OPERATION)){%>--%>
        <li>Add User</li>
        <li>Delete User</li>
        <li>Edit User Info</li>
<%--        <%}if (user.getUserRole().getPermissionList().contains(Permission.POST_CRUD_OPERATION)&&--%>
<%--                user.getPermissions().contains(Permission.POST_CRUD_OPERATION))%>--%>
        <li>Add Post</li>
        <li>Delete Post</li>
        <li>Edit Post</li>
    </ol>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
const message = '<%= request.getAttribute("message")%>'
    alert(message)
</script>
</body>
</html>
