package Controller;

import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//When the user clicks on the playlist name in his profile this servlet will be called
//This servlet will fetch the videos from the playlist
public class ShowPlayListItemsController extends HttpServlet {
    private final ServiceInterface serviceInstance = new ServiceImple();

    protected void getPlayListItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get input
        String userEmail = req.getParameter("userEmail");
        String playListName =  req.getParameter("playListName");

        //set attribute
        req.setAttribute("userEmail",userEmail);
        req.setAttribute("playListName",playListName);
        req.setAttribute("playListItems",serviceInstance.getPlayListItems(userEmail,playListName));

        //goes to playlist items
        req.getRequestDispatcher("playlist-items.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getPlayListItems(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getPlayListItems(req, resp);
    }
}
