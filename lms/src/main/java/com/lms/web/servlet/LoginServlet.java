package com.lms.web.servlet;

import com.lms.model.User;
import com.lms.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    // Servlet lifecycle method
    @Override
    public void init() throws ServletException {
        userService = new UserService();
        System.out.println("LoginServlet initialized");
    }

    // Form handling
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {

            // Session management
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", userOpt.get());

            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("LoginServlet destroyed");
    }
}
