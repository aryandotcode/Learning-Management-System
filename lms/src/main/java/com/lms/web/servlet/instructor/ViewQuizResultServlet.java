package com.lms.web.servlet.instructor;

import com.lms.model.QuizResult;
import com.lms.service.QuizResultService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/instructor/quiz-results")
public class ViewQuizResultServlet extends HttpServlet {

    private final QuizResultService service = new QuizResultService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quizIdStr = request.getParameter("quizId");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Quiz Results</h2>");

        if (quizIdStr == null) {
            out.println("<p>Quiz ID missing</p>");
            out.println("</body></html>");
            return;
        }

        Long quizId = Long.parseLong(quizIdStr);
        List<QuizResult> results = service.getResultsByQuiz(quizId);

        if (results.isEmpty()) {
            out.println("<p>No student has attempted this quiz yet.</p>");
        } else {
            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>Student ID</th><th>Score</th></tr>");
            for (QuizResult r : results) {
                out.println("<tr>");
                out.println("<td>" + r.getStudentId() + "</td>");
                out.println("<td>" + r.getScore() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("<br/><a href='../dashboard'>Back to Dashboard</a>");
        out.println("</body></html>");
    }
}
