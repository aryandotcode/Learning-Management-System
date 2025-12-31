package com.lms.web.servlet.admin;

import com.lms.model.Role;
import com.lms.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/add-user")
public class AddUserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Role role = Role.valueOf(request.getParameter("role"));

        userService.createUser(name, email, password, role);

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
