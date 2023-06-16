package Bean;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.*;

import java.util.ArrayList;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User{
    @Id
    String userEmail;
    @Index
    String name;
    @Index
    String password;

    @Index
    ArrayList<String> playLists;

}
