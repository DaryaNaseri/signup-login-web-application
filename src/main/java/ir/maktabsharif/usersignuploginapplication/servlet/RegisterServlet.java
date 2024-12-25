package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserRequestDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       UserRequestDto userRequestDto = (UserRequestDto) req.getAttribute("optionalUserRequestDto");
        Boolean isTrue = userService.save(userRequestDto);
        if (isTrue) {
            req.setAttribute("message","signup successfully");
            req.getRequestDispatcher("/panel.jsp").forward(req, resp);
        }else {
            req.setAttribute("message","signup failed");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }





    }
}
