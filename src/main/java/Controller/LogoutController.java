package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends HttpServlet {

    private void logoutUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //invalidate session and goes to index
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logoutUser(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logoutUser(req,resp);
    }


}
