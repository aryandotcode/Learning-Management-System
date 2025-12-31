<h2>Add Question</h2>

<form method="post" action="<%=request.getContextPath()%>/instructor/add-question">
    <input type="hidden" name="quizId" value="<%=request.getParameter("quizId")%>">

    Question: <input type="text" name="question" required><br><br>
    A: <input type="text" name="a"><br>
    B: <input type="text" name="b"><br>
    C: <input type="text" name="c"><br>
    D: <input type="text" name="d"><br><br>

    Correct Option (A/B/C/D):
    <input type="text" name="correct" maxlength="1"><br><br>

    <button type="submit">Add Question</button>
</form>

<a href="courses">⬅ Back</a>
