package Utilities;

import DTO.UserDTO;
import DTO.VideoDTO;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.QueryResults;

import java.util.ArrayList;

public class DTOUtilities {
    public static UserDTO entitytoUserDto(Entity entity, ArrayList<VideoDTO> videoDTOList){
        return UserDTO.builder()
                .name(entity.getString("name"))
                .email(entity.getString("mail"))
                .videoDTOList(videoDTOList)
                .password(entity.getString("password"))
                .build();
    }

    public static VideoDTO entityToVideoDto(Entity videoEntity){
        return VideoDTO.builder()
                .link(videoEntity.getString("link"))
                .title(videoEntity.getString("title"))
                .build();
    }

    public static ArrayList<VideoDTO> videoListFromResult(QueryResults<Entity> results){
        ArrayList<VideoDTO> videoDTOList = new ArrayList<>();
        while (results.hasNext()){
            Entity videoEntity = results.next();
            videoDTOList.add(entityToVideoDto(videoEntity));
        }
        return videoDTOList;
    }

    public static VideoDTO EntityToVideoDTO(Entity videoEntity) {
        return VideoDTO.builder()
                .link(videoEntity.getString("link"))
                .title(videoEntity.getString("title"))
                .build();
    }
}
