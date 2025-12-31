<h2>Create Course</h2>

<form method="post" action="<%=request.getContextPath()%>/instructor/create-course">
    Title: <input type="text" name="title" required><br><br>
    Description: <input type="text" name="description"><br><br>
    <button type="submit">Create</button>
</form>

<br>
<a href="courses">⬅ Back</a>
