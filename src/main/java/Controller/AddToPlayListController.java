package Controller;

import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//When the user clicks add to playlist and click on existing playlist this servlet will be called
//This controller will add video to playlist from the playlist jsp page
public class AddToPlayListController extends HttpServlet {

    private static final ServiceInterface serviceInstance = new ServiceImple();

    protected void addVideoToPlayList(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        String playListName = req.getParameter("playListName");
        String videoTitle = req.getParameter("videoTitle");
        String userEmail = req.getParameter("userEmail");
        req.setAttribute("userEmail",userEmail);
        serviceInstance.addVideoToPlaylist(playListName,videoTitle,userEmail);
        req.setAttribute("playList",serviceInstance.getPlayLists(userEmail));

        req.getRequestDispatcher("home.jsp").forward(req,resp);
        //   req.getRequestDispatcher("GoHomeController").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addVideoToPlayList(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addVideoToPlayList(req, resp);
    }
}
