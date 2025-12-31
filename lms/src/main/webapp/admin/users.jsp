<%@ page import="java.util.List" %>
<%@ page import="com.lms.model.User" %>

<html>
<head>
    <title>Admin - Users</title>
</head>
<body>

<h2>All Users</h2>

<a href="add-user.jsp">➕ Add New User</a>
<br><br>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
    </tr>

    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User u : users) {
    %>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getRole() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
