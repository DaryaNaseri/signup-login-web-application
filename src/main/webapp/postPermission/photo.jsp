<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 1/13/2025
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>آپلود عکس پروفایل</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      background-color: #f4f4f4;
    }

    .profile-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 15px;
    }

    .profile-picture {
      width: 150px;
      height: 150px;
      border-radius: 50%;
      overflow: hidden;
      border: 3px solid #ccc;
      background-color: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      position: relative;
    }

    .profile-picture img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .upload-btn {
      position: absolute;
      bottom: 10px;
      background-color: rgba(0, 0, 0, 0.5);
      color: white;
      font-size: 12px;
      padding: 5px 10px;
      border-radius: 10px;
      cursor: pointer;
      display: none;
    }

    .profile-picture:hover .upload-btn {
      display: block;
    }

    input[type="file"] {
      display: none;
    }
  </style>
</head>
<body>
<div class="profile-container">
  <div class="profile-picture" id="profilePicture">
    <img id="previewImage" src="https://via.placeholder.com/150" alt="profile photo">
    <label for="uploadInput" class="upload-btn">change photo</label>
  </div>
  <input type="file" id="uploadInput" accept="image/*" onchange="updateProfilePicture(event)">
</div>

<script>
  function updateProfilePicture(event) {
    const file = event.target.files[0]; // دریافت فایل انتخاب‌شده
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        const previewImage = document.getElementById('previewImage');
        previewImage.src = e.target.result; // به‌روزرسانی عکس پروفایل
      };
      reader.readAsDataURL(file); // تبدیل فایل به Base64
    }
  }
</script>
</body>
</html>