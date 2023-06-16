package Controller;

import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//When the user clicks add to playlist and click on existing playlist this servlet will be called
//This controller will add video to playlist from the playlist jsp page
public class AddToPlayListController extends HttpServlet {

    private static final ServiceInterface serviceInstance = new ServiceImple();

    protected void addVideoToPlayList(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException{

        //get the inputs
        String playListName = req.getParameter("playListName");
        String videoTitle = req.getParameter("videoTitle");
        String userEmail = req.getParameter("userEmail");

        //set attribute
        req.setAttribute("userEmail",userEmail);

        //try to add song to a playlist if duplicate is found throws exception
        try{
            serviceInstance.addVideoToPlaylist(playListName,videoTitle,userEmail);
            //on successful addition goes to home
            req.getRequestDispatcher("GoHomeController").forward(req,resp);
        }catch(Exception e){
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("exception.jsp").forward(req,resp);
        }



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
