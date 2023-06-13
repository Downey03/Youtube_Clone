package Controller;

import DTO.UserDTO;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//When the user logs in this servlet will be called
//it validate the user
//If the user has playlists it will take to  playlist page
//else it will display all videos
public class LoginController extends HttpServlet {

    private ServiceInterface serviceInstance;

    @Override
    public void init() throws ServletException {
        serviceInstance = ServiceInterface.getServiceInstance();
    }

    protected void doLoginProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String userEmail = req.getParameter("userEmail");
        String password = req.getParameter("password");
        try{
            serviceInstance.verifyCredentials(userEmail,password);
            req.setAttribute("userEmail",userEmail);
            req.getRequestDispatcher("GoHomeController").forward(req,resp);
        }catch (Exception e){
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("exception.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException  {
        doLoginProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        doLoginProcess(req, resp);
    }
}
