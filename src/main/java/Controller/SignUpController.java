package Controller;

import DTO.UserDTO;
import Service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SignUpController extends HttpServlet{

    private ServiceInterface serviceInstance;

    @Override
    public void init() throws ServletException {
        if(serviceInstance == null){
            serviceInstance = ServiceInterface.getServiceInstance();
        }
    }

    protected void doSignUpProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get input
        String name = req.getParameter("name");
        String userEmail = req.getParameter("userEmail");
        String password = req.getParameter("password");

        //try to signup, throws exception on duplicate userEmail
        try{
            serviceInstance.createUser(name,userEmail,password);
            req.setAttribute("userEmail",userEmail);
            //on successful signup goes to home
            req.getRequestDispatcher("GoHomeController").forward(req,resp);
        }catch (Exception e){
           req.setAttribute("msg",e.getMessage());
           req.getRequestDispatcher("sign-up-exception.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doSignUpProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doSignUpProcess(req, resp);
    }
}
