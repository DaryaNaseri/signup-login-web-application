<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.EditRequestDto" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.security.Base64PhotoEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 1/13/2025
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>panel</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>


<%
    ResponseDto responseDto = (ResponseDto) session.getAttribute("responseDto");
%>
<div class="wrapper">
    <h2><%=responseDto.getFullName() != null ? responseDto.getFullName() : "Your"%> Information</h2>
    <form>
        <div class="profile-container">
            <div class="profile-picture">
                <img src="data:image/jpeg;base64,<%=responseDto.getPhotoHash() != null ? responseDto.getPhotoHash() : ""%>"
                     alt="Profile Photo">

            </div>
        </div>

        <div class="info-box">
            <p><span>ID: </span> <%=responseDto.getId() != null ? responseDto.getId() : "Not Provided"%>
            </p>
            <p>
                <span>Username: </span> <%=responseDto.getUsername() != null ? responseDto.getUsername() : "Not Provided"%>
            </p>
            <p>
                <span>Full Name: </span> <%=responseDto.getFullName() != null ? responseDto.getFullName() : "Not Provided"%>
            </p>
            <p><span>Email: </span> <%=responseDto.getEmail() != null ? responseDto.getEmail() : "Not Provided"%>
            </p>
            <p><span>Phone Number: </span> <%=responseDto.getPhone() != null ? responseDto.getPhone() : "Not Provided"%>
            </p>
            <p><span>Age: </span> <%=responseDto.getAge() != null ? responseDto.getAge() : "Not Provided"%>
            </p>
        </div>
        <div class="text">
            <h3>Do You Want To Edit? <a href="editProfile.jsp">Edit</a></h3>
        </div>
        <div class="text">
            <h3>Do You Want To Edit Username Or Password? <a href="editUserPass.jsp">Edit Username/Password</a></h3>
        </div>
    </form>
</div>
</body>
</html>
