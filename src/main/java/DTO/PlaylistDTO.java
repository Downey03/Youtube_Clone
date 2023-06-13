package DTO;

import lombok.*;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaylistDTO {
    private ArrayList<String> playlist;
}
