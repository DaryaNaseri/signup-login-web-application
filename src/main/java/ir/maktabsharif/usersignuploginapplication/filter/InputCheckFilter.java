//package ir.maktabsharif.usersignuploginapplication.filter;
//
//import ir.maktabsharif.usersignuploginapplication.model.dto.UserRequestDto;
//import ir.maktabsharif.usersignuploginapplication.service.UserService;
//import ir.maktabsharif.usersignuploginapplication.service.UserServiceImpl;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.io.IOException;
//import java.util.Set;
//
//@WebFilter("/panel")
//public class InputCheckFilter implements Filter {
//    UserService userService = new UserServiceImpl();
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        UserRequestDto userRequestDto = new UserRequestDto(username, password);
//
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
//
//        if (violations.isEmpty()) {
//            request.setAttribute("userRequestDto", userRequestDto);
//            filterChain.doFilter(request, response);
//        } else {
//            for (ConstraintViolation<UserRequestDto> violation : violations) {
//                request.setAttribute("error", violation.getMessage());
//            }
//            request.setAttribute("message", "Failed to register user please try again!");
//            request.getRequestDispatcher("/signup.jsp").forward(request, response);
//        }
//    }
//
//
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}