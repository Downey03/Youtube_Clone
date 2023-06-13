package Service;

import DTO.PlaylistDTO;
import DTO.UserDTO;
import DTO.VideoDTO;

import java.util.ArrayList;

public interface ServiceInterface {
    static ServiceInterface serviceInstance = new ServiceImple();

    public static ServiceInterface getServiceInstance(){
        return serviceInstance;
    }

    UserDTO verifyCredentials(String mail, String password) throws Exception;

    UserDTO createUser(String name, String mail, String password) throws Exception;

    ArrayList<String> getPlayLists(String userEmail) ;

    void createPlaylist(String playlistName,String userEmail) throws Exception;

    void addVideoToPlaylist(String playListName, String videoTitle, String userEmail);

    void deletePlayList(String playListName, String userEmail);

    ArrayList<VideoDTO> getPlayListItems(String userEmail, String playListName);

    ArrayList<VideoDTO> getVideoList();

    void deleteVideo(String userEmail, String playListName, String videoTitle);


    ArrayList<VideoDTO> getVideoList(String searchKeyword);
}