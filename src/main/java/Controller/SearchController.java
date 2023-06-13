package Controller;

import DTO.VideoDTO;
import Service.ServiceImple;
import Service.ServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SearchController extends HttpServlet {
    private static ServiceInterface serviceInstance ;

    @Override
    public void init() throws ServletException {
        if (serviceInstance == null) serviceInstance = new ServiceImple();
    }


    protected void searchItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter("userEmail");
        String searchKeyword = req.getParameter("searchKeyword");
        req.setAttribute("userEmail",userEmail);
        req.setAttribute("videoList",serviceInstance.getVideoList(searchKeyword));
        req.getRequestDispatcher("/home.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        searchItems(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        searchItems(req, resp);
    }
}
