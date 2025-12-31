<%@ page import="java.util.List" %>
<%@ page import="com.lms.model.Course" %>

<h2>My Courses</h2>

<a href="create-course.jsp">➕ Create New Course</a>
<br><br>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
    </tr>

    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");
        for (Course c : courses) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getTitle() %></td>
        <td><%= c.getDescription() %></td>
    </tr>
    <% } %>
</table>

<br>
<a href="<%=request.getContextPath()%>/dashboard">⬅ Back</a>
