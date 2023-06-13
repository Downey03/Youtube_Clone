package DAO;

import DTO.PlaylistDTO;
import DTO.UserDTO;
import DTO.VideoDTO;

import java.util.ArrayList;

public interface DAOinterface {
    static DAOinterface daoInstance = new DAOImple();

    public static DAOinterface getDaoInstance(){
        return daoInstance;
    }

    UserDTO verifyCredentials(String mail, String password) throws Exception;

    UserDTO createUser(String name, String mail, String password) throws Exception;

    ArrayList<String> getPlayLists(String userEmail);

    void createPlaylist(String playlistName,String userEmail) throws Exception;

    void addVideoToPlayList(String playListName, String videoTitle, String userEmail);

    void deletePlayList(String playListName, String userEmail);

    ArrayList<VideoDTO> getPlayListItems(String userEmail, String playListName);

    ArrayList<VideoDTO> getVideoList();

    void deleteVideo(String userEmail, String playListName, String videoTitle);

    ArrayList<VideoDTO> getVideoList(String searchKeyword);
}
