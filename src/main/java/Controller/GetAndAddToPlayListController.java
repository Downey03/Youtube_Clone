package Controller;

import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//when the user clicks add to playlist button this servlet will be called
//this servlet will only display the available playlist
//When the user selects a playlist the video Title will be forwarded where the video will be added
public class GetAndAddToPlayListController extends HttpServlet {
    private ServiceInterface serviceInstance;

    @Override
    public void init() throws ServletException {
        if(serviceInstance == null){
            serviceInstance = ServiceInterface.getServiceInstance();
        }
    }

    protected void getPlaylist(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

        //get input
        String userEmail = req.getParameter("userEmail");
        String videoTitle = req.getParameter("videoTitle");

        //set attributes
        req.setAttribute("userEmail",userEmail);
        req.setAttribute("playLists",serviceInstance.getPlayLists(userEmail));
        req.setAttribute("videoTitle",videoTitle);

        //goes to list of playlist
        req.getRequestDispatcher("list-of-playlists.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getPlaylist(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       getPlaylist(req,resp);
    }
}
