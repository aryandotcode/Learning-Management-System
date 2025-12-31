package com.lms.web.servlet.instructor;

import com.lms.model.Question;
import com.lms.service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/instructor/add-question")
public class AddQuestionServlet extends HttpServlet {

    private final QuestionService questionService = new QuestionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Question q = new Question();
        q.setQuizId(Long.parseLong(request.getParameter("quizId")));
        q.setQuestion(request.getParameter("question"));
        q.setOptionA(request.getParameter("a"));
        q.setOptionB(request.getParameter("b"));
        q.setOptionC(request.getParameter("c"));
        q.setOptionD(request.getParameter("d"));
        q.setCorrectOption(request.getParameter("correct").charAt(0));

        questionService.addQuestion(q);

        response.sendRedirect(request.getContextPath() +
                "/instructor/add-question.jsp?quizId=" + q.getQuizId());
    }
}
