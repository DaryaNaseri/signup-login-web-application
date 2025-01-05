package ir.maktabsharif.usersignuploginapplication.servlet.filter;

import ir.maktabsharif.usersignuploginapplication.model.dto.AdminRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/login")
public class LoginCheckFilter implements Filter {
    private final UserService userService = new UserServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();


        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username.equals("AdminAdmin") && password.equals("AdminAdmin")) {
            AdminRequestDto adminRequestDto = new AdminRequestDto(username, password);
            request.setAttribute("user", adminRequestDto);
            filterChain.doFilter(request, response);
        }else {
            UserSignupRequestDto userSignupRequestDto =
                    new UserSignupRequestDto(username, password);

            Optional<UserResponseDto> findUser = userService.findByUserName(userSignupRequestDto);

            if (findUser.isPresent()) {
                session.setAttribute("user", findUser.get());
                filterChain.doFilter(request, response);
            }
            else {
request.setAttribute("message", "you don't have any account ,signup");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
}
        }




    }



    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
