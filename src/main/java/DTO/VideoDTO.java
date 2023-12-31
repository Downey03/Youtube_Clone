package DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VideoDTO {
    private String link;
    private String title;

    @Override
    public String toString() {
        return super.toString();
    }
}
