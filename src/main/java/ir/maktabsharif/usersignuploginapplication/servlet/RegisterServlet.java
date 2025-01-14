package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.SignupRequestDto;
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
import java.util.Set;

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

if (!password.equals(confirmPassword)) {
    req.setAttribute("message", "password and confirm password do not match");
    req.getRequestDispatcher("signup.jsp").forward(req, resp);
}else {

    SignupRequestDto signupRequestDto =
            SignupRequestDto.builder().username(username).password(password).build();


    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();
    Set<ConstraintViolation<SignupRequestDto>> validate = validator.validate(signupRequestDto);
    if (!validate.isEmpty()) {
        StringBuilder errors = new StringBuilder();
        for (ConstraintViolation<SignupRequestDto> constraintViolation : validate) {
            errors.append(constraintViolation.getMessage()).append(" \n");
        }
        req.setAttribute("message", "please enter valid parameters : " + errors);
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    } else {
        Boolean isTrue = userService.save(signupRequestDto);

        if (!isTrue) {
            req.setAttribute("message", "signup failed, please try again");
            resp.sendRedirect(req.getContextPath() + "/signup.jsp");
        } else {
            req.setAttribute("message", "signup successfully");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");

        }

    }
}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getPathInfo());
    }
}
