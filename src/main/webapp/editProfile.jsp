<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto" %>
<%@ page import="ir.maktabsharif.usersignuploginapplication.security.Base64PhotoEncoder" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="css/styles.css">

</head>
<body>
<%
    ResponseDto responseDto = (ResponseDto) session.getAttribute("responseDto");
    if (responseDto == null) {
        responseDto = ResponseDto.builder().build();
    }
%>

<div class="wrapper">
    <h2>Complete Your Personal Info <%=responseDto.getUsername()%>
    </h2>

    <form action="profile" method="post" enctype="multipart/form-data">
        <div class="profile-container">
            <div class="profile-picture" id="profilePicture">

                <img id="previewImage" src="<%= (responseDto.getPhotoHash() != null && !responseDto.getPhotoHash().isEmpty())
    ? "data:image/jpeg;base64," + responseDto.getPhotoHash()
    : "https://via.placeholder.com/150" %>" alt="Profile Photo">

                <label for="uploadInput" class="upload-btn">Change Photo</label>
            </div>
            <input type="file" id="uploadInput" name="photo" accept="image/*" onchange="updateProfilePicture(event)">
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

        <div class="policy">
            <input type="checkbox" required>
            <h3>I accept all terms & conditions</h3>
        </div>
        <div class="input-box button">
            <input type="submit" value="Submit">
        </div>
        <div class="text">
            <h3>Do You Want To Cancel? <a href="dashboard.jsp">Cancel</a></h3>
        </div>
        <div class="text">
            <h3>Do You Want To Change Your Username Or Password? <a href="editUserPass.jsp">Edit Username/Password</a>
            </h3>
        </div>
    </form>
</div>
<script>
    function updateProfilePicture(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('previewImage').src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    }
</script>

</body>
</html>