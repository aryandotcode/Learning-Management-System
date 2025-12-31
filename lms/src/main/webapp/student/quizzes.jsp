<%@ page import="java.util.List, com.lms.model.Quiz" %>
<h3>Available Quizzes</h3>

<%
    List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
    for (Quiz q : quizzes) {
%>
<form action="attempt-quiz" method="get">
    <input type="hidden" name="quizId" value="<%= q.getId() %>"/>
    <button type="submit"><%= q.getTitle() %></button>
</form>
<% } %>
