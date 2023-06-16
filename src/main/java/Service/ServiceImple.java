package Service;

import DAO.DAOImpleObjectify;
import DAO.DAOinterface;
import DTO.UserDTO;
import DTO.VideoDTO;

import java.util.ArrayList;

public class ServiceImple implements ServiceInterface{

    private static final DAOinterface daoInstance = new DAOImpleObjectify();

    @Override
    public void verifyCredentials(String mail, String password) throws Exception {

        try {
             daoInstance.verifyCredentials(mail,password);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void createUser(String name, String userEmail, String password) throws Exception {

        try{
            daoInstance.createUser(name,userEmail,password);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getPlayLists(String userEmail)  {

        return daoInstance.getPlayLists(userEmail);

    }

    @Override
    public void createPlaylist(String playlistName,String userEmail) throws Exception {
        daoInstance.createPlaylist(playlistName,userEmail);
    }

    @Override
    public void addVideoToPlaylist(String playListName, String videoTitle, String userEmail) throws Exception {
        daoInstance.addVideoToPlayList(playListName,videoTitle,userEmail);
    }

    @Override
    public void deletePlayList(String playListName, String userEmail) {
        daoInstance.deletePlayList(playListName,userEmail);
    }

    @Override
    public ArrayList<VideoDTO> getPlayListItems(String userEmail, String playListName) {
        return daoInstance.getPlayListItems(userEmail,playListName);
    }

    @Override
    public ArrayList<VideoDTO> getVideoList() {
        return daoInstance.getVideoList();
    }

    @Override
    public void deleteVideo(String userEmail, String playListName, String videoTitle) {
        daoInstance.deleteVideo(userEmail,playListName,videoTitle);
    }

    @Override
    public ArrayList<VideoDTO> getVideoList(String searchKeyword) {
        return daoInstance.getVideoList(searchKeyword);
    }
}
