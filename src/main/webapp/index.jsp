<%@ page import="ir.maktabsharif.usersignuploginapplication.repository.UserRolePermissionRepository" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<% //for create permission and user-role
    UserRolePermissionRepository.createUserRoleAndPermission();%>

<div class="wrapper">
    <h2>Welcome to Our Website</h2>
    <div class="input-box-button-input">
        <button style="background-color: #1abc9c" class="button" onclick="location.href='login.jsp'">Login</button>
    </div>
    <div class="input-box-button-input">
        <button style="background-color: #1abc9c" class="button" onclick="location.href='signup.jsp'">Sign Up</button>
    </div>
</div>
</body>
</html>