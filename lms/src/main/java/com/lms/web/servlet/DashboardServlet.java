package com.lms.web.servlet;

import com.lms.model.Role;
import com.lms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Session validation
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 2️⃣ Get logged-in user
        User user = (User) session.getAttribute("loggedUser");

        // 3️⃣ Role-based dashboard redirection
        if (user.getRole() == Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/dashboard/admin.jsp");
        } else if (user.getRole() == Role.INSTRUCTOR) {
            response.sendRedirect(request.getContextPath() + "/dashboard/instructor.jsp");
        } else if (user.getRole() == Role.STUDENT) {
            response.sendRedirect(request.getContextPath() + "/dashboard/student.jsp");
        } else {
            // fallback safety
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
