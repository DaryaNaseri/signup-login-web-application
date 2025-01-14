package ir.maktabsharif.usersignuploginapplication.servlet;

import ir.maktabsharif.usersignuploginapplication.model.dto.EditRequestDto;
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

@WebServlet("/profile")
public class EditProfileServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String fullName = req.getParameter("fullName");
        String age = req.getParameter("age");

        HttpSession session = req.getSession();
        ResponseDto oldResponseDto = (ResponseDto)session.getAttribute("responseDto");
String username = (String) session.getAttribute("username");
String password = (String) session.getAttribute("password");


        EditRequestDto newRequestDto = EditRequestDto
                .builder()
                .id(oldResponseDto.getId())
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .fullName(fullName)
                .age(age)
                .build();


            Boolean isItComplete = userService.update(newRequestDto);

            if (isItComplete) {
                req.setAttribute("message", "edit was successful");

                //todo : maybe this is wrong
                //session.setAttribute("responseDto", newRequestDto);
                session.setAttribute("newResponseDto", newRequestDto);

                resp.sendRedirect(req.getContextPath() + "/profilePanel.jsp");
            }else {
                //todo: you changed it to session it was req
                session.setAttribute("message", "please try again");
                req.getRequestDispatcher("/profilePanel.jsp").forward(req, resp);
            }


    }
}
