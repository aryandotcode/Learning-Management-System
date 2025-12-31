package com.lms.web.servlet.instructor;

import com.lms.model.User;
import com.lms.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/instructor/courses")
public class InstructorCoursesServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User instructor = (User) session.getAttribute("loggedUser");

        request.setAttribute(
                "courses",
                courseService.findByInstructor(instructor.getId())
        );

        request.getRequestDispatcher("/instructor/courses.jsp")
                .forward(request, response);
    }
}
