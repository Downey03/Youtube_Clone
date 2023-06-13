package DAO;

import DTO.UserDTO;
import DTO.VideoDTO;
import Utilities.DTOUtilities;
import com.google.cloud.datastore.*;

import java.util.ArrayList;
import java.util.List;

public class DAOImple implements DAOinterface{


    private static final Datastore datastore = DatastoreOptions.newBuilder().setProjectId("sound-groove-380715")
            .build().getService();


//    private static final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private static Key getUserKey(String userEmail){
       return datastore.newKeyFactory().setKind("user").newKey(userEmail);
    }

    private static Key getVideoKey(String videoTitle){
        return datastore.newKeyFactory().setKind("YTLink").newKey(videoTitle);
    }
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
    public UserDTO verifyCredentials(String userEmail, String password) throws Exception {

        //try to get the user from DB
        Key key = getUserKey(userEmail);
        Entity entity = datastore.get(key);

        //if no user found throw exception
        if(entity==null) throw new Exception("User Not Found");
        //if the password is wrong throw exception
        if(!entity.getString("password").equals(password)) throw new Exception("Invalid password");

        //return the user entity
        return UserDTO.builder()
                .name(entity.getString("name"))
                .email(entity.getString("mail"))
                .password(entity.getString("password"))
                .build();
    }

    @Override
    public UserDTO createUser(String name, String mail, String password) throws Exception {
        //checks if the mail has already been used
        Query<Entity> query = Query.newEntityQueryBuilder()
                    .setKind("user")
                     .setFilter(StructuredQuery.PropertyFilter.eq("mail",mail))
                    .build();

        QueryResults<Entity> results= datastore.run(query);
        //if it is used throw exception
        if(results.hasNext()) throw new Exception("User Already Found");
        //else create a new user
        Entity entity = Entity.newBuilder(getUserKey(mail))
                .set("name",name)
                .set("mail",mail)
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

        return DTOUtilities.entitytoUserDto(entity,videoDTOList);
    }

    @Override
    public ArrayList<String> getPlayLists(String userEmail) {
        Key key = getUserKey(userEmail);
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("playlist")
                .setFilter(StructuredQuery.PropertyFilter.hasAncestor(key))
                .build();
        QueryResults<Entity> results = datastore.run(query);

        ArrayList<String> playlistDTOSList = new ArrayList<>();
        while (results.hasNext()){
            Entity entity = results.next();
            playlistDTOSList.add(entity.getString("name"));
        }

        return playlistDTOSList;
    }

    @Override
    public void createPlaylist(String playlistName,String userEmail) throws Exception {
        Key userKey = getUserKey(userEmail);
        StructuredQuery<Entity> query = Query.newEntityQueryBuilder()
                .setKind("playlist")
                .setFilter(StructuredQuery.CompositeFilter.and(StructuredQuery.PropertyFilter.eq("name",playlistName)
                , StructuredQuery.PropertyFilter.hasAncestor(userKey)))
                .build();
        QueryResults<Entity> results = datastore.run(query);

        if(results.hasNext()) throw new Exception("Already a PlayList Found With the Same Name");

        ListValue.Builder listBuilder = ListValue.newBuilder();

        Key key = getPlayListKey(playlistName,userEmail,userKey);
        Entity entity = Entity.newBuilder(key)
                .set("id",playlistName+userEmail)
                .set("name",playlistName)
                .set("videoList",listBuilder.addValue("Charlie Puth - Cheating on You"
                        ,"Charlie Puth - Dangerously"
                        ,"Charlie Puth - Dangerously").build())
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
