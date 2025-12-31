<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>LMS Login</title>
</head>
<body>

<h2>LMS Login</h2>

<form action="login" method="post">
    Email: <input type="text" name="email" /><br/><br/>
    Password: <input type="password" name="password" /><br/><br/>
    <input type="submit" value="Login"/>
</form>

<p style="color:red;">
    ${error}
</p>

</body>
</html>
