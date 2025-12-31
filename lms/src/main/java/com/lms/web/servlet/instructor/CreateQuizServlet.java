package com.lms.web.servlet.instructor;

import com.lms.model.Quiz;
import com.lms.service.QuizService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/instructor/create-quiz")
public class CreateQuizServlet extends HttpServlet {

    private final QuizService quizService = new QuizService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long courseId = Long.parseLong(request.getParameter("courseId"));
        String title = request.getParameter("title");

        Quiz quiz = new Quiz();
        quiz.setCourseId(courseId);
        quiz.setTitle(title);

        quizService.createQuiz(quiz);

        response.sendRedirect(request.getContextPath() +
                "/instructor/add-question.jsp?quizId=" + quiz.getId());
    }
}
