package com.lms.web.servlet.student;

import com.lms.service.CourseContentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/student/course-content")
public class ViewCourseContentServlet extends HttpServlet {

    private final CourseContentService contentService = new CourseContentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long courseId = Long.parseLong(request.getParameter("courseId"));
        request.setAttribute("contents", contentService.getContentsByCourse(courseId));
        request.getRequestDispatcher("/student/course-content.jsp")
                .forward(request, response);
    }
}
