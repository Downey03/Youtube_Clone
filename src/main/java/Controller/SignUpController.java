package Controller;

import DTO.UserDTO;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpController extends HttpServlet{

    private ServiceInterface serviceInstance;

    @Override
    public void init() throws ServletException {
        if(serviceInstance == null){
            serviceInstance = ServiceInterface.getServiceInstance();
        }
    }

    protected void doSignUpProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String mail = req.getParameter("email");
        String password = req.getParameter("password");
        try{
            UserDTO user = serviceInstance.createUser(name,mail,password);
            req.setAttribute("userEmail",user.getEmail());
            req.getRequestDispatcher("GoHomeController");
            req.setAttribute("videoList",user.getVideoDTOList());
           // req.getRequestDispatcher(resp.encodeURL(req.getContextPath()+"/home.jsp")).forward(req,resp);
         //   resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath()+"/home.jsp"));

            req.getRequestDispatcher("home.jsp").forward(req,resp);
        }catch (Exception e){

           resp.getWriter().println(e.fillInStackTrace());
           req.setAttribute("msg",e.getMessage());
         req.getRequestDispatcher("exception.jsp").forward(req,resp);
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
