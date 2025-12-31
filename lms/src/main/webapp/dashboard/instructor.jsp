<%@ page import="com.lms.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<h2>Instructor Dashboard</h2>
<p>Welcome, <b><%= user.getName() %></b></p>

<form action="../instructor/create-course" method="get">
    <button type="submit">Create Course</button>
</form>
<br/>

<form action="../instructor/upload-content" method="get">
    <button type="submit">Upload Course Content</button>
</form>
<br/>

<form action="../instructor/create-quiz" method="get">
    <button type="submit">Create Quiz</button>
</form>
<br/>

<!-- ✅ NEW: View Quiz Results -->
<form action="../instructor/quiz-results" method="get">
    Quiz ID:
    <input type="number" name="quizId" required />
    <button type="submit">View Quiz Results</button>
</form>

<br/><br/>
<a href="../logout">Logout</a>
