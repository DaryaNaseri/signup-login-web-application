<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panel</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

    <%
        ResponseDto responseDto = (ResponseDto) session.getAttribute("responseDto");
    if (responseDto != null && "ADMIN".equals(responseDto.getUserRole().getRoleName())){%>
    <div class="wrapper">
        <h2>Welcome to Your Panel</h2>

        <div class="input-box-button-input">
        <button style="background-color: #1abc9c" class="button" onclick="location.href='addUser.jsp'">addUser</button>
    </div>
    <div class="input-box-button-input">
        <button style="background-color: #1abc9c" class="button" onclick="location.href='editUser.jsp'">editUser</button>
    </div>
    <div class="input-box-button-input">
        <button style="background-color: #1abc9c" class="button" onclick="location.href='deleteUser.jsp'">deleteUser</button>
    </div>
    </div>
    <%}%>
    <div class="wrapper">
        <h2>Welcome to Your Panel</h2>

        <div class="input-box-button-input">
            <button style="background-color: #1abc9c" class="button" onclick="location.href='addPost.jsp'">addPost</button>
        </div>
        <div class="input-box-button-input">
            <button style="background-color: #1abc9c" class="button" onclick="location.href='editPost.jsp'">editPost</button>
        </div>
        <div class="input-box-button-input">
            <button style="background-color: #1abc9c" class="button" onclick="location.href='deletePost.jsp'">deletePost</button>
        </div>
        <div class="input-box-button-input">
            <button style="background-color: #1abc9c" class="button" onclick="location.href='editProfile.jsp'">show Profile</button>
        </div>
    </div>


</body>
</html>