package Controller;

import Utilities.Utilities;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String searchKeyword = request.getParameter("searchKeyword");
        String userEmail = request.getParameter("userEmail");
        if(Utilities.checkSearchKeyword(searchKeyword)) chain.doFilter(request, response);
        else{
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            req.setAttribute("userEmail",userEmail);
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath()+"/home.jsp"));
        }
    }

    @Override
    public void destroy() {

    }
}
