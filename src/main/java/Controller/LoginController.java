package Controller;

import DTO.UserDTO;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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

        //get input
        String userEmail = req.getParameter("userEmail");
        String password = req.getParameter("password");

        //try to validate, throws exception for wrong credentials
        try{
            serviceInstance.verifyCredentials(userEmail,password);
            req.getSession().setAttribute("userEmail",userEmail);
            //on successful validation logs in
            req.getRequestDispatcher("GoHomeController").forward(req,resp);

        }catch (Exception e){
            req.getSession().invalidate();
            if(e.getMessage().equals("Invalid Email")) req.getSession().setAttribute("emailWarn",e.getMessage());
            else req.getSession().setAttribute("passwordWarn",e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
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
