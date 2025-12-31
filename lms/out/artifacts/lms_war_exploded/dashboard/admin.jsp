<%@ page import="com.lms.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<h2>Admin Dashboard</h2>
<p>Welcome, <%= user.getName() %></p>

<ul>
    <li>User Management</li>
    <li>Course Management</li>
    <li>System Analytics</li>
</ul>

<a href="../logout">Logout</a>
