<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Registration or Sign Up </title>
    <link rel="stylesheet" href="css/styles.css">

</head>
<body>

<div class="wrapper">
    <h2>Registration</h2>
    <form action="signup" method="post">
        <div class="input-box">
            <input type="text" name="username" placeholder="Enter your username" required>
        </div>
        <div class="input-box">
            <input type="password" name="password" placeholder="Create password" required>
        </div>
        <div class="input-box">
            <input type="password" name="confirmPassword" placeholder="Confirm password" required>
        </div>
        <div class="policy">
            <input type="checkbox" required>
            <h3>I accept all terms & condition</h3>
        </div>
        <div class="input-box button">
            <input type="Submit" value="Register Now" >
        </div>
        <div class="text">
            <h3>Already have an account? <a href="login.jsp">Login now</a></h3>
        </div>
    </form>
</div>
<script>
const message = '<%= session.getAttribute("message")%>'
alert(message)
</script>
</body>
</html>