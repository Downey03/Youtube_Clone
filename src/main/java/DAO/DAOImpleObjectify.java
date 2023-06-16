package DAO;

import Bean.PlayList;
import Bean.User;
import Bean.YTLink;
import DTO.VideoDTO;
import Service.ObjectifyInitializer;
import Utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAOImpleObjectify implements DAOinterface{


    //gets a user from database
    static User getUser(String userEmail){
        return ObjectifyInitializer.ofy().load().type(User.class).id(userEmail).now();
    }

    //saves a user to the database
    static void saveUser(User user){
        ObjectifyInitializer.ofy().save().entity(user).now();
    }

    @Override
    public void verifyCredentials(String userEmail, String password) throws Exception {

        //try to get the user
        User user = ObjectifyInitializer.ofy().load().type(User.class).id(userEmail).now();

        //if user not found throw exception
        if(user == null) throw new Exception("Invalid Email");

        //if user found and password is wrong throw exception
        if(!user.getPassword().equals(password)) throw new Exception("Invalid Password");

        //user validated successfully
    }

    @Override
    public void createUser(String name, String userEmail, String password) throws Exception {

        //check if the user already exists
        User user = ObjectifyInitializer.ofy().load().type(User.class).id(userEmail).now();
        if(user != null) throw new Exception("Email Already Registered");

        //if not exists, creates a user
        user = User.builder()
                .userEmail(userEmail)
                .name(name)
                .password(password)
                .playLists(new ArrayList<>())
                .build();

        //saves user
        ObjectifyInitializer.ofy().save().entity(user).now();
    }

    @Override
    public void createPlaylist(String playlistName, String userEmail) throws Exception {

        //check if the user already have a playlist under same name
        User user = ObjectifyInitializer.ofy().load().type(User.class).id(userEmail).now();
        ArrayList<String> playLists = user.getPlayLists();
        if(playLists == null) playLists = new ArrayList<>();
        if(playLists.contains(playlistName.trim())) throw new Exception("Playlist Already Found");

        //if not add playlist to the user
        playLists.add(playlistName);
        user.setPlayLists(playLists);

        //build a playlist
        PlayList playList = PlayList.builder()
                .id(playlistName+userEmail)
                .playListName(playlistName.trim())
                .userEmail(userEmail)
                .videoList(new ArrayList<>())
                .build();

        //save the entities
        ObjectifyInitializer.ofy().save().entity(user).now();
        ObjectifyInitializer.ofy().save().entity(playList).now();

    }

    // get the user and returns the name of the playlists the user have
    @Override
    public ArrayList<String> getPlayLists(String userEmail) {

        User user = ObjectifyInitializer.ofy().load().type(User.class).id(userEmail).now();

        return user.getPlayLists();
    }



    @Override
    public void addVideoToPlayList(String playListName, String videoTitle, String userEmail) throws Exception {

        //get the playlist and ytlink entity
        PlayList playList = ObjectifyInitializer.ofy().load().type(PlayList.class).id(playListName+userEmail).now();
        YTLink ytVideo = ObjectifyInitializer.ofy().load().type(YTLink.class).id(videoTitle).now();

        ArrayList<YTLink> playListItems;
        if(playList.getVideoList() == null) playListItems = new ArrayList<>();
        else playListItems = playList.getVideoList();

        //if playlist already has a song with same name throw exception
        if(playListItems.contains(ytVideo)) throw new Exception("Song Already Found in Playlist");

        //else add the video
        playListItems.add(ytVideo);
        playList.setVideoList(playListItems);

        //save playlist
        ObjectifyInitializer.ofy().save().entity(playList).now();
    }

    @Override
    public void deletePlayList(String playListName, String userEmail) {

        //get the user and delete the playlist from the user's details
        User user = ObjectifyInitializer.ofy().load().type(User.class).id(userEmail).now();
        if(user.getPlayLists()!=null){
            ArrayList<String> playlists = user.getPlayLists();
            playlists.remove(playListName);
            user.setPlayLists(playlists);
        }

        //save the user
        ObjectifyInitializer.ofy().save().entity(user).now();

        //delete the playlist
        ObjectifyInitializer.ofy().delete().type(PlayList.class).id(playListName+userEmail).now();

    }

    @Override
    public ArrayList<VideoDTO> getPlayListItems(String userEmail, String playListName) {

        //get the playlist
        PlayList playList = ObjectifyInitializer.ofy().load().type(PlayList.class).id(playListName+userEmail).now();

        //if there is no song returns null
        ArrayList<YTLink> ytLinks = playList.getVideoList();
        if(ytLinks == null) return null;

        //Builds a videoDto arraylist
        ArrayList<VideoDTO> videoList = new ArrayList<>();
        for (YTLink video : ytLinks){
            VideoDTO videoDTO = VideoDTO.builder()
                    .title(video.getTitle())
                    .link(video.getLink())
                    .build();
            videoList.add(videoDTO);
        }

        //returns the videoDto arraylist
        return videoList;
    }



    @Override
    public void deleteVideo(String userEmail, String playListName, String videoTitle) {

        //get the playlist
        PlayList playList = ObjectifyInitializer.ofy().load().type(PlayList.class).id(playListName+userEmail).now();

        //deletes the video from the playlist
        ArrayList<YTLink> ytLinks = playList.getVideoList();
        for (YTLink video : ytLinks){
            if(video.getTitle().equals(videoTitle)){
                ytLinks.remove(video);
                break;
            }
        }
        playList.setVideoList(ytLinks);

        //save the playlist
        ObjectifyInitializer.ofy().save().entity(playList);

    }

    @Override
    public ArrayList<VideoDTO> getVideoList() {

        //get the ytlink kind entities
        List<YTLink> results = ObjectifyInitializer.ofy().load().type(YTLink.class).list();

        //build a videoDto arraylist
        ArrayList<VideoDTO> videoList = new ArrayList<>();
        for (YTLink vidoe : results){
            VideoDTO videoDTO = VideoDTO.builder()
                    .title(vidoe.getTitle())
                    .link(vidoe.getLink())
                    .build();
            videoList.add(videoDTO);
        }

        //return the videoDto arraylist
        return videoList;
    }

    @Override
    public ArrayList<VideoDTO> getVideoList(String searchKeyword) {

        //get the ytlink kind entities
        List<YTLink> ytLinks = ObjectifyInitializer.ofy().load().type(YTLink.class).list();

        //build a videoDto arraylist with entries that match with search keyword
        ArrayList<VideoDTO> videoList = new ArrayList<>();
        for (YTLink video : ytLinks){
            if(!video.getTitle().toLowerCase().contains(searchKeyword.trim().toLowerCase())) continue;
            VideoDTO videoDTO = VideoDTO.builder()
                    .title(video.getTitle())
                    .link(video.getLink())
                    .build();
            videoList.add(videoDTO);
        }

        //returns videoDto arraylist
        return videoList;
    }

    public void saveVideo(String videoTitle, String videoLink) {
        YTLink ytLink = YTLink.builder()
                .link(videoLink)
                .title(videoTitle)
                .build();

        ObjectifyInitializer.ofy().save().entity(ytLink).now();
    }
}
