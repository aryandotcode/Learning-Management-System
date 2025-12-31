package com.lms.web.servlet.admin;

import com.lms.model.User;
import com.lms.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class ListUsersServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = userService.listAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
    }
}
