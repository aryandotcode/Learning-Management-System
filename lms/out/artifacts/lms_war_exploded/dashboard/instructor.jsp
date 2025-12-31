<%@ page import="com.lms.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<h2>Instructor Dashboard</h2>
<p>Welcome, <%= user.getName() %></p>

<ul>
    <li>Create Courses</li>
    <li>Upload Content</li>
    <li>Create Quizzes</li>
</ul>

<a href="../logout">Logout</a>
