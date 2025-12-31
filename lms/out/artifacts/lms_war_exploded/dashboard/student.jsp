<%@ page import="com.lms.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<h2>Student Dashboard</h2>
<p>Welcome, <%= user.getName() %></p>

<ul>
    <li>View Courses</li>
    <li>Enroll Courses</li>
    <li>Attempt Quizzes</li>
</ul>

<a href="../logout">Logout</a>
