package com.lms.web.servlet.student;

import com.lms.service.QuizService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/student/attempt-quiz")
public class AttemptQuizServlet extends HttpServlet {

    private final QuizService quizService = new QuizService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long quizId = Long.parseLong(request.getParameter("quizId"));
        request.setAttribute("questions", quizService.getQuestions(quizId));
        request.setAttribute("quizId", quizId);
        request.getRequestDispatcher("/student/attempt-quiz.jsp").forward(request, response);
    }
}
