<html>
<head>
    <title>Add User</title>
</head>
<body>

<h2>Add New User</h2>

<form method="post" action="<%=request.getContextPath()%>/admin/add-user">
    Name: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>

    Role:
    <select name="role">
        <option value="ADMIN">ADMIN</option>
        <option value="INSTRUCTOR">INSTRUCTOR</option>
        <option value="STUDENT">STUDENT</option>
    </select>
    <br><br>

    <button type="submit">Create User</button>
</form>

<br>
<a href="<%=request.getContextPath()%>/admin/users">⬅ Back to Users</a>

</body>
</html>
