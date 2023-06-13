package Controller;

import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteVideoFromPlayList extends HttpServlet {

    private static ServiceInterface serviceInstance;

    @Override
    public void init() throws ServletException {
        if(serviceInstance == null) serviceInstance = new ServiceImple();
    }

    protected void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter("userEmail");
        String playListName = req.getParameter("playListName");
        String videoTitle = req.getParameter("videoTitle");
        serviceInstance.deleteVideo(userEmail,playListName,videoTitle);
        req.setAttribute("userEmail",userEmail);
        req.setAttribute("playListName",playListName);
        req.getRequestDispatcher("ShowPlayListItemsController").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteVideo(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteVideo(req, resp);
    }
}
