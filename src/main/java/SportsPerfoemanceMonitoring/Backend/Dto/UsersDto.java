package SportsPerfoemanceMonitoring.Backend.Dto;

import lombok.Data;
@Data

public class UsersDto {
    private String username;
    private String password;
    private String role; // This will be 'athlete', 'coach', or 'admin'
}
