package ir.maktabsharif.usersignuploginapplication.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class WhitelistFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Set<String> whiteSet = new HashSet<>();
        try (InputStream resourceAsStream = getClass().getResourceAsStream("/whitelist.txt");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
               whiteSet.add(line);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;


        String servletPath = httpServletRequest.getServletPath();

       if (whiteSet.contains(servletPath)){
           httpServletRequest.getRequestDispatcher(servletPath)
                   .forward(servletRequest,servletResponse);
       } else {
           HttpSession session = httpServletRequest.getSession(true);
           session.setAttribute("requestedPath", servletPath);
           filterChain.doFilter(servletRequest,servletResponse);
       }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
