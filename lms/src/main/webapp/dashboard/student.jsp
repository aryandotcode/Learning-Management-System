<%@ page import="com.lms.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<h2>Student Dashboard</h2>
<p>Welcome, <b><%= user.getName() %></b></p>

<form action="../student/view-courses" method="get">
    <button type="submit">View Courses</button>
</form>
<br/>

<form action="../student/enroll" method="get">
    <button type="submit">Enroll in Course</button>
</form>
<br/>

<form action="../student/attempt-quiz" method="get">
    <button type="submit">Attempt Quiz</button>
</form>
<br/><br/>

<a href="../logout">Logout</a>
