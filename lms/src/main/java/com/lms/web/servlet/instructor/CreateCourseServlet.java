package com.lms.web.servlet.instructor;

import com.lms.model.User;
import com.lms.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/instructor/create-course")
public class CreateCourseServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User instructor = (User) session.getAttribute("loggedUser");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        courseService.createCourse(title, description, instructor.getId());

        response.sendRedirect(request.getContextPath() + "/instructor/courses");
    }
}
