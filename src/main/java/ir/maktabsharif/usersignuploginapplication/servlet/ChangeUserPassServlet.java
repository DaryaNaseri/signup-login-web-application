package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.EditUserPassDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto;
import ir.maktabsharif.usersignuploginapplication.security.BCryptPasswordEncode;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;

@WebServlet("/changeUserPass")
public class ChangeUserPassServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");


        HttpSession session = req.getSession();
        ResponseDto oldResponseDto = (ResponseDto) session.getAttribute("responseDto");

        if (!BCryptPasswordEncode.verifyBCryptPassword(oldPassword, oldResponseDto.getPassword())) {
            req.setAttribute("message", "old password is incorrect,please try again");
            resp.sendRedirect(req.getContextPath() + "/editUserPass.jsp");
        } else {
            if (oldPassword.equals(newPassword)) {
                req.setAttribute("message", "new password must be different from old password,please try again");
                resp.sendRedirect(req.getContextPath() + "/editUserPass.jsp");
            } else {
                if (!newPassword.equals(confirmPassword)) {
                    req.setAttribute("message", "password and confirm password do not match");
                    req.getRequestDispatcher("editUserPass.jsp").forward(req, resp);
                } else {

                    EditUserPassDto editUserPassDto =
                            EditUserPassDto.builder().id(oldResponseDto.getId()).username(username).password(newPassword).build();


                    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                    Validator validator = validatorFactory.getValidator();
                    Set<ConstraintViolation<EditUserPassDto>> validate = validator.validate(editUserPassDto);
                    if (!validate.isEmpty()) {
                        StringBuilder errors = new StringBuilder();
                        for (ConstraintViolation<EditUserPassDto> constraintViolation : validate) {
                            errors.append(constraintViolation.getMessage()).append(" \n");
                        }
                        req.setAttribute("message", "please enter valid parameters : " + errors);
                        req.getRequestDispatcher("editUserPass.jsp").forward(req, resp);
                    } else {
                        Boolean isTrue = userService.updateUserOrPass(editUserPassDto);

                        if (!isTrue) {
                            req.setAttribute("message", "edit failed, please try again");

                            resp.sendRedirect(req.getContextPath() + "/editUserPass.jsp");
                        } else {
                            req.setAttribute("message", "edit successfully");
                            resp.sendRedirect(req.getContextPath() + "/login.jsp");

                        }

                    }}
                }
                //////////////////////////////////////////////////////
            }
        }
    }
