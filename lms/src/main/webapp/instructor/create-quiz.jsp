<h2>Create Quiz</h2>

<form method="post" action="<%=request.getContextPath()%>/instructor/create-quiz">
    Course ID: <input type="number" name="courseId" required><br><br>
    Quiz Title: <input type="text" name="title" required><br><br>
    <button type="submit">Create Quiz</button>
</form>

<a href="courses">⬅ Back</a>
