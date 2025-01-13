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
    <h2>You Can Complete Or Edit Your Profile</h2>

    <div class="profile-container">
        <div class="profile-picture" id="profilePicture">
            <img id="previewImage" src="https://via.placeholder.com/150" alt="profile photo">
            <label for="uploadInput" class="upload-btn">Change Photo</label>
        </div>
        <input type="file" id="uploadInput" accept="image/*" onchange="updateProfilePicture(event)">
    </div>

    <form action="profile" method="post">


        <div class="input-box">
            <label class="text" for="username">username: </label>

            <input type="text" name="username" id="username" placeholder="Enter your password"
                   value="<%=responseDto.getPassword() != null ? responseDto.getUsername() : ""%>" required>
        </div>
        <div class="input-box">
            <input type="password" name="password" placeholder="Create password"
                   value="<%=responseDto.getPassword() != null ? responseDto.getPassword() : ""%>" required>
        </div>
        <div class="input-box">
            <input type="text" name="fullName" placeholder="Enter your Full Name"
                   value="<%=responseDto.getFullName() != null ? responseDto.getFullName() : ""%>" required>
        </div>
        <div class="input-box">
            <input type="text" name="email" placeholder="Enter your email"
                   value="<%=responseDto.getEmail() != null ? responseDto.getEmail() : ""%>" required>
        </div>
        <div class="input-box">
            <input type="text" name="phone" placeholder="Enter your Phone Number"
                   value="<%=responseDto.getPhone() != null ? responseDto.getPhone() : ""%>" required>
        </div>
        <div class="input-box">
            <input type="text" name="age" placeholder="Enter your age"
                   value="<%=responseDto.getAge() != null ? responseDto.getAge() : ""%>" required>
        </div>
<%--        <div class="input-box">--%>
<%--            <label class="text" for="gender">gender </label>--%>
<%--            <select id="gender" name="gender">--%>
<%--                <option value="FEMALE">female</option>--%>
<%--                <option value="MALE">male</option>--%>
<%--            </select>--%>
<%--        </div>--%>
        <div class="policy">
            <input type="checkbox" required>
            <h3>I accept all terms & conditions</h3>
        </div>
        <div class="input-box button">
            <input type="submit" value="Submit">
        </div>
        <div class="text">
            <h3>Do You Want To Cancel? <a href="panel.jsp">Cancel</a></h3>
        </div>
    </form>
</div>
<script>
    function updateProfilePicture(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const previewImage = document.getElementById('previewImage');
                previewImage.src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    }
</script>


</body>
</html>