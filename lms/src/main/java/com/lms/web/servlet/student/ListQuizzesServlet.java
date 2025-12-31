package com.lms.web.servlet.student;

import com.lms.service.QuizService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/student/quizzes")
public class ListQuizzesServlet extends HttpServlet {

    private final QuizService quizService = new QuizService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long courseId = Long.parseLong(request.getParameter("courseId"));
        request.setAttribute("quizzes", quizService.getQuizzesByCourse(courseId));
        request.getRequestDispatcher("/student/quizzes.jsp").forward(request, response);
    }
}
