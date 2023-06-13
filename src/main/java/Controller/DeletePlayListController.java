package Controller;

import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//When the user click on delete playlist in the profile page this servlet will be called
//This controller will delete the playlist from the profile page
public class DeletePlayListController extends HttpServlet {
    private ServiceInterface serviceInstance = new ServiceImple();

    protected void deletePlayList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter("userEmail");
        String playListName = req.getParameter("playListName");
        req.setAttribute("userEmail",userEmail);
        serviceInstance.deletePlayList(playListName,userEmail);
        req.getRequestDispatcher("ProfileController").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deletePlayList(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deletePlayList(req, resp);
    }
}
