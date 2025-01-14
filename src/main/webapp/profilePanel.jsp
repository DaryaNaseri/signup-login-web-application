<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.EditRequestDto" %><%--
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
    EditRequestDto responseDto = (EditRequestDto) session.getAttribute("responseDto");
%>
<div class="wrapper">
    <h2>Client Information</h2>

        <div class="profile-container">
            <div class="profile-picture">
                <img src="<%=responseDto.getPhotoHash() != null ? responseDto.getPhotoHash() : "https://via.placeholder.com/150"%>" alt="Profile Photo">
            </div>
        </div>

    <div class="info-box">
        <p><span>Phone Number:</span> <%=responseDto.getId() != null ? responseDto.getId() : "Not Provided"%></p>
        <p><span>Full Name:</span> <%=responseDto.getFullName() != null ? responseDto.getFullName() : "Not Provided"%></p>
        <p><span>Email:</span> <%=responseDto.getEmail() != null ? responseDto.getEmail() : "Not Provided"%></p>
        <p><span>Phone Number:</span> <%=responseDto.getPhone() != null ? responseDto.getPhone() : "Not Provided"%></p>
        <p><span>Age:</span> <%=responseDto.getAge() != null ? responseDto.getAge() : "Not Provided"%></p>
    </div>
    <div class="input-box-button-input">
        <button style="background-color: #1abc9c" class="button" onclick="location.href='editProfile.jsp'">Edit Profile</button>
    </div>
</div>
</body>
</html>
