package ir.maktabsharif.usersignuploginapplication.servlet.filter;

import ir.maktabsharif.usersignuploginapplication.model.dto.UserRequestDto;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class LoginCheckFilter implements Filter {
    private UserService userService = new UserServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserRequestDto userRequestDto = new UserRequestDto(username, password);

        Boolean haveAnyAccount = userService.findByUserName(userRequestDto);

        if (haveAnyAccount) {
            filterChain.doFilter(request, response);
        }
        else {
            request.setAttribute("message", "you don't have any account ,signup");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }


    }



    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
