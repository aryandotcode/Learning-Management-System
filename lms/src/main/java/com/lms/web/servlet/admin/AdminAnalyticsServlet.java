package com.lms.web.servlet.admin;

import com.lms.service.CourseService;
import com.lms.service.UserService;
import com.lms.service.EnrollmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/analytics")
public class AdminAnalyticsServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("userCount", userService.listAll().size());
        request.setAttribute("courseCount", courseService.listAll().size());
        request.setAttribute("enrollmentCount", enrollmentService.findAll().size());

        request.getRequestDispatcher("/admin/analytics.jsp").forward(request, response);
    }
}
