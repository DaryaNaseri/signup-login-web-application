<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>
<body>

<div class="wrapper">
    <h2>Login</h2>
    <form action="login" method="post">
        <div class="input-box">
            <input type="text" name="username" placeholder="Enter your username" required>
        </div>
        <div class="input-box">
            <input type="password" name="password" placeholder="Enter your password" required>
        </div>
        <div class="input-box button">
            <input type="Submit" value="Login" >
        </div>
        <div class="text">
            <h3>Don't have an account? <a href="signup.jsp">SignUp now</a></h3>
        </div>
    </form>
</div>
<script>
const message = '<%= session.getAttribute("message")%>'
alert(message)
</script>
</body>
</html>