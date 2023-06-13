package Controller;

import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//When user clicks on create playlist from the profile page this servlet will be called
//This controller will a add playlist from the profile page
public class CreateNewPlayListController extends HttpServlet {

    private ServiceInterface serviceInstance;

    @Override
    public void init() throws ServletException {
        if(serviceInstance == null) serviceInstance = new ServiceImple();
    }

    protected void createPlaylist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter("userEmail");
        String playlistName = req.getParameter("playListName");
        req.setAttribute("userEmail",userEmail);
        try{
            serviceInstance.createPlaylist(playlistName,userEmail);
            req.getRequestDispatcher("ProfileController").forward(req,resp);
        }catch (Exception e){
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("exception.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createPlaylist(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createPlaylist(req, resp);
    }
}
