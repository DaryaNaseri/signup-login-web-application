package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.LoginRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);


        if (Optional.ofNullable(username).isPresent() && Optional.ofNullable(password).isPresent()){

            LoginRequestDto loginRequestDto =
                    LoginRequestDto.builder()
                            .username(username)
                            .password(password)
                            .build();

            Optional<ResponseDto> userOpt =
                    userService.findByUserNameAndPassword(loginRequestDto);


            if (userOpt.isPresent()){
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                session.setAttribute("responseDto",userOpt.get());
                session.setAttribute("message","login success");


                String requestedServletPath = (String) session.getAttribute("requestedPath");
                if (requestedServletPath != null) {
                    resp.sendRedirect(req.getContextPath() + requestedServletPath);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                }
            } else {
                session.setAttribute("message","Username or Password is incorrect, please try again");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } else {
            session.setAttribute("message","Username or Password is incorrect");
            //resp.sendRedirect("login.jsp?error=Username or Password is incorrect");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }

}
