package com.lms.web.servlet.admin;

import com.lms.model.Course;
import com.lms.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/courses")
public class AdminCourseServlet extends HttpServlet {

    private final CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Course> courses = courseService.listAll();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/admin/courses.jsp").forward(request, response);
    }
}
