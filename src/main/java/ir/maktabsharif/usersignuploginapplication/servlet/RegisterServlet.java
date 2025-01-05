package ir.maktabsharif.usersignuploginapplication.servlet;

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

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       UserSignupRequestDto userSignupRequestDto = (UserSignupRequestDto) req.getAttribute("optionalUserRequestDto");
       HttpSession session = req.getSession();
        Boolean isTrue = userService.save(userSignupRequestDto);
        if (isTrue) {
            req.setAttribute("message","signup successfully");
            session.setAttribute("username", userSignupRequestDto.getUsername());
            session.setAttribute("password", userSignupRequestDto.getPassword());


            resp.sendRedirect(req.getContextPath() + "/panel.jsp");
        }else {
            req.setAttribute("message","signup failed, please try again");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getPathInfo());
    }
}
