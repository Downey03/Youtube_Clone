package DAO;

import DTO.UserDTO;
import DTO.VideoDTO;
import Utilities.DTOUtilities;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class DAOImple implements DAOinterface{

    private static GoogleCredentials getCredentials(){
        try{
           return GoogleCredentials.fromStream(new ByteArrayInputStream(System.getenv("552d30126d68b9af23ba1e6ea507215a5cf51427").getBytes()));
        }catch(Exception e){
            return null;
        }
    }





    //Get datastore connection
    private static final Datastore datastore = DatastoreOptions.newBuilder()
            .setProjectId("sound-groove-380715")
    //            .setCredentials(getCredentials())
            .build().getService();


    //private static final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    //generate user key by passing string(ID)
    private static Key getUserKey(String userEmail){
       return datastore.newKeyFactory().setKind("user").newKey(userEmail);
    }

    //generate video key by passing string(ID)
    private static Key getVideoKey(String videoTitle){
        return datastore.newKeyFactory().setKind("YTLink").newKey(videoTitle);
    }

    //generate playlist key by passing string(ID)
    private static Key getPlayListKey(String playlistName,String userMail,Key userKey){
        return datastore.newKeyFactory()
                .addAncestors(PathElement.of("user",userMail))
                .setKind("playlist").newKey(playlistName+userMail);
    }

//    private static Key getPlayListKey(String playlistKey,String userMail){
//        return datastore.newKeyFactory()
//                .setKind("playlist")
//                .addAncestor(PathElement.of("user",))
//                .newKey(playlistKey);
//    }


    @Override
    public void verifyCredentials(String userEmail, String password) throws Exception {

        //try to get the user from DB
        Key key = getUserKey(userEmail);
        Entity entity = datastore.get(key);

        //if no user found throw exception
        if(entity==null) throw new Exception("Invalid Email");
        //if the password is wrong throw exception
        if(!entity.getString("password").equals(password)) throw new Exception("Invalid password");

        //return the user entity

    }

    @Override
    public void createUser(String name, String userEmail, String password) throws Exception {


        //checks if the mail already exists
        Query<Entity> query = Query.newEntityQueryBuilder()
                    .setKind("user")
                    .setFilter(StructuredQuery.PropertyFilter.eq("mail",userEmail))
                    .build();
        QueryResults<Entity> results= datastore.run(query);

        //if the mail exists
        if(results.hasNext()) throw new Exception("User Already Found");


        //else create a new user
        Entity entity = Entity.newBuilder(getUserKey(userEmail))
                .set("name",name)
                .set("mail",userEmail)
                .set("password",password)
                .build();
        datastore.put(entity);

        //get 10 videos from database
        query = Query.newEntityQueryBuilder()
                .setKind("YTLink")
                .setLimit(10)
                .build();

        results = datastore.run(query);
        ArrayList<VideoDTO> videoDTOList = DTOUtilities.videoListFromResult(results);

    }

    @Override
    public ArrayList<String> getPlayLists(String userEmail) {

        //get the userKey
        Key key = getUserKey(userEmail);

        //search playlists which owned by the user
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("playlist")
                .setFilter(StructuredQuery.PropertyFilter.hasAncestor(key))
                .build();
        QueryResults<Entity> results = datastore.run(query);

        //build list of string(playlist name)
        ArrayList<String> playlistDTOSList = new ArrayList<>();
        while (results.hasNext()){
            Entity entity = results.next();
            playlistDTOSList.add(entity.getString("name"));
        }

        //return the list
        return playlistDTOSList;
    }

    @Override
    public void createPlaylist(String playlistName,String userEmail) throws Exception {

        //get the userKey
        Key userKey = getUserKey(userEmail);

        //Check if the user already have a playlist with the same name (playlistName)
        StructuredQuery<Entity> query = Query.newEntityQueryBuilder()
                .setKind("playlist")
                .setFilter(StructuredQuery.CompositeFilter.and(StructuredQuery.PropertyFilter.eq("name",playlistName)
                , StructuredQuery.PropertyFilter.hasAncestor(userKey)))
                .build();
        QueryResults<Entity> results = datastore.run(query);

        //if he have a playlist throw exception
        if(results.hasNext()) throw new Exception("Already a PlayList Found With the Same Name");

        ListValue.Builder listBuilder = ListValue.newBuilder();

        Key key = getPlayListKey(playlistName,userEmail,userKey);
        Entity entity = Entity.newBuilder(key)
                .set("id",playlistName+userEmail)
                .set("name",playlistName)
                .set("videoList",listBuilder.build())
                .build();

        datastore.put(entity);
    }

    @Override
    public void addVideoToPlayList(String playListName, String videoTitle,String userEmail) {

        //get the playlist entity from DB
        Key key = getPlayListKey(playListName,userEmail,getUserKey(userEmail));
        Entity entity =datastore.get(key);

        //get the ListValue and Store the new Value
        List<Value<?>> listValue = entity.getList("videoList");
        ListValue.Builder builder = ListValue.newBuilder();
        for(Value<?> value :listValue){
            builder.addValue(value);
        }
        builder.addValue(videoTitle);

        //rebuild the entity
        entity = Entity.newBuilder(entity)
                        .set("videoList",builder.build())
                        .build();

        //store the entity
        datastore.put(entity);

    }

    @Override
    public void deletePlayList(String playListName, String userEmail) {
        //Build a key for the playlist
        Key key = getPlayListKey(playListName,userEmail,getUserKey(userEmail));

        //delete the entity from DB
        datastore.delete(key);
    }

    @Override
    public ArrayList<VideoDTO> getPlayListItems(String userEmail, String playListName) {
        //get the playlist entity
        Key key = getPlayListKey(playListName,userEmail,getUserKey(userEmail));
        Entity entity = datastore.get(key);

        //get the ListValue from the entity
        List<Value<?>> valueList = entity.getList("videoList");

        //Create a Array to store and return values
        ArrayList<VideoDTO> videoDTOList = new ArrayList<>();

        //get the video from DB and store it in ArrayList
        for(Value<?> value : valueList){
            if(value instanceof StringValue){
                System.out.println(value);
              //  Entity videoEntity = datastore.get(getVideoKey(((StringValue) value).get()));
                System.out.println((String) value.get());
                Entity videoEntity = datastore.get(getVideoKey((String) value.get()));
                videoDTOList.add(DTOUtilities.EntityToVideoDTO(videoEntity));
            }
        }

        //return the ArrayList
        return videoDTOList;
    }

    @Override
    public ArrayList<VideoDTO> getVideoList() {
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("YTLink")
                .setLimit(10)
                .build();

        QueryResults<Entity> results = datastore.run(query);
        return DTOUtilities.videoListFromResult(results);
    }

    @Override
    public ArrayList<VideoDTO> getVideoList(String searchKeyword) {
//        String queryString = "SELECT * FROM YTLink WHERE title IN (searchKeyword)";
//
//        Query<Entity> query = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, queryString)
//                .build();
//        QueryResults<Entity> results = datastore.run(query);
//        return DTOUtilities.videoListFromResult(results);

        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("YTLink")
                .setLimit(10)
                .build();

        QueryResults<Entity> results = datastore.run(query);
        ArrayList<VideoDTO> videoDTOList = new ArrayList<>();

        while (results.hasNext()){
            Entity videoEntity = results.next();
            String videoString = videoEntity.getString("title").toLowerCase();
            String searchString = searchKeyword.toLowerCase();
            if(videoString.contains(searchString)) videoDTOList.add(DTOUtilities.entityToVideoDto(videoEntity));
        }
        return videoDTOList;
    }

    @Override
    public void deleteVideo(String userEmail, String playListName, String videoTitle) {

        //get the playList from the DB
        Key key = getPlayListKey(playListName,userEmail,getUserKey(userEmail));
        Entity entity = datastore.get(key);

        //get the list of Video from the playList
        List<Value<?>> listValue = entity.getList("videoList");

        ListValue.Builder builder = ListValue.newBuilder();

        //remove the video from the list
        for(Value<?> value :listValue){
            if(value.get().equals(videoTitle)) continue;
            builder.addValue(value);
        }

        //rebuild the entity
        entity = Entity.newBuilder(entity)
                .set("videoList",builder.build())
                .build();

        //store the entity
        datastore.put(entity);


    }
}
