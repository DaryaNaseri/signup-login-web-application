package ir.maktabsharif.usersignuploginapplication.servlet.filter;

import ir.maktabsharif.usersignuploginapplication.excepion.MustLoginException;
import ir.maktabsharif.usersignuploginapplication.excepion.UsernameOrPasswordIncorrectException;
import ir.maktabsharif.usersignuploginapplication.model.dto.LoginRequestDto;
import ir.maktabsharif.usersignuploginapplication.model.dto.ResponseDto;
import ir.maktabsharif.usersignuploginapplication.security.BCryptPasswordEncode;
import ir.maktabsharif.usersignuploginapplication.service.UserService;
import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter implements Filter {

    private UserService userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);


            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");

            //String hashedPassword = MD5PasswordEncoder.hashWithMD5(password);


            if (Optional.ofNullable(username).isPresent() && Optional.ofNullable(password).isPresent()) {

                LoginRequestDto loginRequestDto =
                        LoginRequestDto.builder()
                                .username(username)
                                .password(password)
                                .build();

                Optional<ResponseDto> userOpt =
                        userService.findByUserNameAndPassword(loginRequestDto);


                if (userOpt.isPresent()) {
                    if (username.equals(userOpt.get().getUsername()) &&
                            BCryptPasswordEncode.verifyBCryptPassword(password, userOpt.get().getPassword())) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                    else {
                        servletRequest.setAttribute("message" , "username or password incorrect,try again");
                        response.sendRedirect(request.getContextPath() + "/login.jsp");

                    }
                }else {
                    servletRequest.setAttribute("message" , "username or password incorrect,try again");
                    response.sendRedirect(request.getContextPath() + "/login.jsp");

                }
            }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
