<%@ page import="java.util.List, com.lms.model.CourseContent" %>

<h2>Course Materials</h2>

<%
    List<CourseContent> contents =
            (List<CourseContent>) request.getAttribute("contents");

    if (contents == null || contents.isEmpty()) {
%>
<p>No content uploaded yet.</p>
<%
} else {
    for (CourseContent c : contents) {
%>
<p>
    📄 <b><%= c.getFileName() %></b><br/>
    Path: <%= c.getFilePath() %>
</p>
<hr/>
<%
        }
    }
%>

<a href="../dashboard">Back to Dashboard</a>
