<%@ page import="ir.maktabsharif.usersignuploginapplication.model.entity.User" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.service.UserService" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.entity.Permission" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto" %>
<%@ page import="java.util.Optional" %><%--
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
</head>
<body>
<div class="container">
    <ol>

        <%
            UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("userResponseDto");
            if (userResponseDto != null && "ADMIN".equals(userResponseDto.getUserRole().getRoleName())) {
        %>
        <li>Add User</li>
        <li>Delete User</li>
        <li>Edit User Info</li>
        <%
            }
        %>


        <li>Add Post</li>
        <li>Delete Post</li>
        <li>Edit Post</li>

    </ol>
</div>
<script>
const message = '<%= request.getAttribute("message")%>'
    alert(message)
</script>
</body>
</html>
