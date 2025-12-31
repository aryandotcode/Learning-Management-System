<%@ page import="java.util.List, com.lms.model.Question" %>

<h3>Attempt Quiz</h3>

<form action="submit-quiz" method="post">
    <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") %>"/>

    <%
        List<Question> qs = (List<Question>) request.getAttribute("questions");
        for (int i = 0; i < qs.size(); i++) {
            Question q = qs.get(i);
    %>

    <p><%= q.getQuestion() %></p>
    <input type="radio" name="q<%= i %>" value="A"/> <%= q.getOptionA() %><br/>
    <input type="radio" name="q<%= i %>" value="B"/> <%= q.getOptionB() %><br/>
    <input type="radio" name="q<%= i %>" value="C"/> <%= q.getOptionC() %><br/>
    <input type="radio" name="q<%= i %>" value="D"/> <%= q.getOptionD() %><br/>

    <% } %>

    <button type="submit">Submit Quiz</button>
</form>
