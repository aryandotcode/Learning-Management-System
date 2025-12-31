<%@ page import="java.util.List" %>
<%@ page import="com.lms.model.QuizResult" %>

<h2>Quiz Results</h2>

<table border="1">
    <tr>
        <th>Student ID</th>
        <th>Score</th>
    </tr>

    <%
        List<QuizResult> list =
                (List<QuizResult>) request.getAttribute("results");
        for(QuizResult r : list){
    %>
    <tr>
        <td><%= r.getStudentId() %></td>
        <td><%= r.getScore() %></td>
    </tr>
    <% } %>
</table>
