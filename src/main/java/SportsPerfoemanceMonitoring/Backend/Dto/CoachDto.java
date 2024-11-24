package SportsPerfoemanceMonitoring.Backend.Dto;

import lombok.Data;

@Data
public class CoachDto {
    private String username;
    private String password;
    private Long coachId;
    private String firstName;
    private String lastName;

}