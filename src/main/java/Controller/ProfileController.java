package Controller;

import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//this servlet act as a redirection to home page
//it will get list of playlists and videoList
public class ProfileController extends HttpServlet {

    private static final ServiceInterface serviceInstance = new ServiceImple();

    protected void getProfile(HttpServletRequest req, HttpServletResponse resp)  throws ServletException,IOException{
       String userEmail = req.getParameter("userEmail");
       req.setAttribute("userEmail",userEmail);
       req.setAttribute("playLists",serviceInstance.getPlayLists(userEmail));
       req.setAttribute("videoList",serviceInstance.getVideoList());
       req.getRequestDispatcher("profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getProfile(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getProfile(req, resp);
    }
}
