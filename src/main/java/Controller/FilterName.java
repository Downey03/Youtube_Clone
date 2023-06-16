package Controller;

import javax.servlet.*;
import java.io.IOException;

public class FilterName implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String playListName = request.getParameter("playListName");
        String checkName = playListName.trim();
        if(checkName.length()>0) chain.doFilter(request, response);
        else {
            System.out.println("flter else blocl");
        }
    }

    @Override
    public void destroy() {

    }
}
