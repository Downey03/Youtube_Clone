package Controller;

import DAO.DAOImpleObjectify;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveYTLink extends HttpServlet {

    DAOImpleObjectify daoImpleObjectify = new DAOImpleObjectify();

    protected void saveVideo (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String videoTitle = req.getParameter("videoTitle");
        String videoLink = req.getParameter("videoLink");
        daoImpleObjectify.saveVideo(videoTitle,videoLink);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveVideo(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveVideo(req, resp);
    }
}
