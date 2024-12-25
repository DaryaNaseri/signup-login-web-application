<%--
  Created by IntelliJ IDEA.
  User: Darya
  Date: 12/23/2024
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8">
<%--<meta name="viewport"--%>
<%--      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
<%--<meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>Instagram clone</title>
<body>
<form action="signup" method="post">
    <div class="form-group d">
        <input type="text" name="username" class="form-control " id="username" aria-describedby="usernameHelp"  placeholder="Enter username">
        <small id="usernameHelp" class="form-text text-muted text-danger">Enter your Username in English please.</small>
    </div>
<%--    <div class="form-group">--%>
<%--        <label for="email">Email address</label>--%>
<%--        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">--%>
<%--        <small id="emailHelp" class="form-text text-muted text-danger">We'll never share your email with anyone else.</small>--%>
<%--    </div>--%>
    <div class="form-group">
        <input type="text" class="form-control" id="password" name="password" placeholder="Enter password">
        <small id="passwordHelp" class="form-text text-muted text-danger">password must be at least 8 characters.</small>

    </div>
    <input type="submit" value="signup">
<%--    <button type="submit" class="btn btn-primary">Sign up</button>--%>
</form>

<script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossOrigin="anonymous"></script>
<script>
    const message = '<%=request.getAttribute("message")%>'
    alert(message)
</script>
</body>
</html>
