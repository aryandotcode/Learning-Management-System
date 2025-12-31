<%@ page import="java.util.List" %>
<%@ page import="com.lms.model.Course" %>

<html>
<head>
    <title>Admin - Course Management</title>
</head>
<body>

<h2>All Courses</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Instructor ID</th>
    </tr>

    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");
        if (courses != null) {
            for (Course c : courses) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getTitle() %></td>
        <td><%= c.getDescription() %></td>
        <td><%= c.getInstructorId() %></td>
    </tr>
    <%
            }
        }
    %>
</table>

<br>
<a href="<%=request.getContextPath()%>/dashboard">⬅ Back</a>

</body>
</html>
