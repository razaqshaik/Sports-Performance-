package SportsPerfoemanceMonitoring.Backend.Dto;

import lombok.Data;
import java.util.Date;

@Data
public class AthleteDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private float height;
    private float weight;
    private String category;
    private String photoUrl;
}
