<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile </title>
    <link rel="stylesheet" href="css/styles.css">

</head>
<body>
<%
    ResponseDto responseDto = (ResponseDto) session.getAttribute("responseDto");
    if (responseDto == null) {
        responseDto = ResponseDto.builder().build(); // Create a new empty object to avoid NullPointerException
    }
%>

<div class="wrapper">
    <h2>Change your Username or Password</h2>
    <form action="changeUserPass" method="post">


        <div class="input-box">
            <input type="text" name="username" id="username" placeholder="Enter your password"
                   value="<%=responseDto.getPassword() != null ? responseDto.getUsername() : ""%>" required>
        </div>
        <div class="input-box">
            <input type="password" name="oldPassword" placeholder="Enter old password" required>
        </div>
        <div class="input-box">
            <input type="password" name="newPassword" placeholder="Create password" required>
        </div>
        <div class="input-box">
            <input type="password" name="confirmPassword" placeholder="Confirm password" required>
        </div>
        <div class="policy">
            <input type="checkbox" required>
            <h3>I accept all terms & conditions</h3>
        </div>
        <div class="input-box button">
            <input type="submit" value="Submit">
        </div>
        <div class="text">
            <h3>Do You Want To Cancel? <a href="profilePanel.jsp">Cancel</a></h3>
        </div>
    </form>
</div>
<script>
    const message = '<%= session.getAttribute("message")%>'
    alert(message)
</script>
</body>
</html>
