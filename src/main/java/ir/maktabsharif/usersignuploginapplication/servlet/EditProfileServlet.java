package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.EditRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.SignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;
import ir.maktabsharif.usersignuploginapplication.util.UploadImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import java.util.Optional;
import java.util.Set;

@WebServlet("/profile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5,  // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class EditProfileServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String fullName = req.getParameter("fullName");
        String age = req.getParameter("age");


        Optional<String> imageHash = UploadImageUtil.convertImage(req, resp, "photo");
        String photoHash = imageHash.orElse(null);

        ResponseDto oldResponseDto = (ResponseDto) session.getAttribute("responseDto");


        EditRequestDto newRequestDto = EditRequestDto
                .builder()
                .id(oldResponseDto.getId())
                .username(oldResponseDto.getUsername())
                .password(oldResponseDto.getPassword())
                .email(email)
                .photoHash(photoHash)
                .phone(phone)
                .fullName(fullName)
                .age(age)
                .build();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<EditRequestDto>> validate = validator.validate(newRequestDto);
        if (!validate.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<EditRequestDto> constraintViolation : validate) {
                errors.append(constraintViolation.getMessage()).append(" \n");
            }
            session.setAttribute("message", errors);
            req.getRequestDispatcher("editProfile.jsp").forward(req, resp);
        } else {

            Optional<ResponseDto> responseDtoAfterUpdate = userService.update(newRequestDto);

            if (responseDtoAfterUpdate.isPresent()) {
                session.setAttribute("message", "edit was successful");

                //session.setAttribute("responseDto", newRequestDto);
                session.setAttribute("responseDto", responseDtoAfterUpdate.get());

                resp.sendRedirect(req.getContextPath() + "/profilePanel.jsp");
            } else {
                session.setAttribute("message", "please try again");
                req.getRequestDispatcher("/profilePanel.jsp").forward(req, resp);
            }
        }

    }
}
