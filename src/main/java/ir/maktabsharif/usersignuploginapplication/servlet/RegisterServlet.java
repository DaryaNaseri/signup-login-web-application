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
import java.util.Set;

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserRequestDto userRequestDto = new UserRequestDto(username, password);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);

        if (violations.isEmpty()) {
            Boolean save = userService.save(userRequestDto);
            if (save) {
                req.setAttribute("message", "User registered successfully!");

                req.getRequestDispatcher("/panel.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Failed to register user please try again!");
                req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Failed to register user please try again!");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }


    }
}
