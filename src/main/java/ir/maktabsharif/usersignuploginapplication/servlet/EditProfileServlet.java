package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.EditRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto;
import ir.maktabsharif.usersignuploginapplication.model.entity.User;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class EditProfileServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String fullName = req.getParameter("fullName");
        String age = req.getParameter("age");

        HttpSession session = req.getSession();
        ResponseDto responseDto = (ResponseDto)session.getAttribute("responseDto");


        EditRequestDto newRequestDto = EditRequestDto
                .builder()
                .id(responseDto.getId())
                .email(email)
                .password(responseDto.getPassword())
                .phone(phone)
                .fullName(fullName)
                .username(username)
                .age(age)
                .build();


            Boolean isItComplete = userService.update(newRequestDto);

            if (isItComplete) {

                //todo : maybe this is wrong
                session.setAttribute("responseDto", newRequestDto);

                resp.sendRedirect("/profile.jsp");
            }else {
                req.setAttribute("message", "please try again");
                req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            }


    }
}
