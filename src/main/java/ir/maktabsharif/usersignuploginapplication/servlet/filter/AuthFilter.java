package ir.maktabsharif.usersignuploginapplication.servlet.filter;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserResponseDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.UserSignupRequestDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

//@WebFilter("/*")
public class AuthFilter implements Filter {

    private UserService userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        HttpSession session = request.getSession();

        Optional<String> username = Optional.ofNullable((String) session.getAttribute("username"));
        Optional<String> password = Optional.ofNullable((String) session.getAttribute("password"));


        if (username.isPresent() && password.isPresent()) {

            Optional<UserResponseDto> optionalUserName =
                    userService.findByUserNameAndPassword(new UserSignupRequestDto(username.get(), password.get()));

            if (optionalUserName.isPresent()) {
                if (optionalUserName.get().getUsername().equals(username.get()) &&
                        optionalUserName.get().getPassword().equals(password.get())) {

                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }

        //filter will work again
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        //server side rendering:
        // request.getRequestDispatcher("/index.jsp").forward(request, response);


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
