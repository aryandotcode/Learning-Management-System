<%@ page import="com.lms.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>

<h2>Admin Dashboard</h2>
<p>Welcome, <b><%= user.getName() %></b></p>

<hr>

<!-- User Management -->
<form action="<%=request.getContextPath()%>/admin/users" method="get">
    <button type="submit">User Management</button>
</form>
<br/>

<!-- Course Management -->
<form action="<%=request.getContextPath()%>/admin/courses" method="get">
    <button type="submit">Course Management</button>
</form>
<br/>

<!-- System Analytics -->
<form action="<%=request.getContextPath()%>/admin/analytics" method="get">
    <button type="submit">System Analytics</button>
</form>

<hr>

<a href="<%=request.getContextPath()%>/logout">Logout</a>

</body>
</html>
