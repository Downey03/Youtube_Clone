package DTO;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private ArrayList<VideoDTO> videoDTOList;
    private ArrayList<String> listOfPlayList;
}
