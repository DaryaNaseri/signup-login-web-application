package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.setAttribute("username", req.getParameter("username"));
        session.setAttribute("password", req.getParameter("password"));


        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("userResponseDto");

        if (userResponseDto == null) {
            userResponseDto = (UserResponseDto) req.getAttribute("userResponseDto");
            if (userResponseDto != null) {
                session.setAttribute("userResponseDto", userResponseDto);
            }
        }

        String requestedServletPath =(String) req.getAttribute("requestedServletPath");
        if (requestedServletPath != null) {
            req.getRequestDispatcher(requestedServletPath).forward(req, resp);
        }else {
            req.getRequestDispatcher("/panel.jsp").forward(req, resp);
        }

    }



}
