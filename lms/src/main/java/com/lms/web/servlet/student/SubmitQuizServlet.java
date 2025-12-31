package com.lms.web.servlet.student;

import com.lms.model.User;
import com.lms.service.QuizService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student/submit-quiz")
public class SubmitQuizServlet extends HttpServlet {

    private final QuizService quizService = new QuizService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long quizId = Long.parseLong(request.getParameter("quizId"));

        List<Character> answers = new ArrayList<>();
        int i = 0;
        while (request.getParameter("q" + i) != null) {
            answers.add(request.getParameter("q" + i).charAt(0));
            i++;
        }

        int score = quizService.evaluateQuiz(quizId, answers);
        request.setAttribute("score", score);
        request.getRequestDispatcher("/student/result.jsp").forward(request, response);
    }
}
