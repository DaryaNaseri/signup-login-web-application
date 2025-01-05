package ir.maktabsharif.usersignuploginapplication.servlet.filter;

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

@WebFilter("/*")
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

        if (request.getServletPath().equals("/login.jsp") ||
                request.getServletPath().equals("/signup.jsp")||
                request.getServletPath().equals("/signup")
                || request.getServletPath().equals("/filterPage.jsp")
                || request.getServletPath().equals("/index.jsp")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();

        Optional<String> username =  Optional.ofNullable( (String) session.getAttribute("username"));
        Optional<String> password =  Optional.ofNullable( (String) session.getAttribute("password"));


        if (username.isPresent() && password.isPresent()) {
            Optional<UserResponseDto> optionalUserName =
                    userService.findByUserName(new UserSignupRequestDto(username.get(), password.get()));
            if (optionalUserName.isPresent()) {
                UserResponseDto userResponseDto = optionalUserName.get();
                if (userResponseDto.getUsername().equals(username.get()) && userResponseDto.getPassword().equals(password.get())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }

       response.sendRedirect(request.getContextPath() + "/index.jsp");
       //request.getRequestDispatcher("/filterPage.jsp").forward(request, response);


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
