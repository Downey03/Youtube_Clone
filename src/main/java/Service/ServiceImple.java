package Service;

import DAO.DAOImple;
import DAO.DAOinterface;
import DTO.UserDTO;
import DTO.VideoDTO;

import java.util.ArrayList;

public class ServiceImple implements ServiceInterface{

    private static final DAOinterface daoInstance = new DAOImple();

    @Override
    public UserDTO verifyCredentials(String mail, String password) throws Exception {
        UserDTO userDTO;
        try {
            userDTO = daoInstance.verifyCredentials(mail,password);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return userDTO;
    }

    @Override
    public UserDTO createUser(String name, String mail, String password) throws Exception {
        UserDTO userDTO;
        try{
           userDTO =  daoInstance.createUser(name,mail,password);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return userDTO;
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
    public void addVideoToPlaylist(String playListName, String videoTitle, String userEmail) {
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
