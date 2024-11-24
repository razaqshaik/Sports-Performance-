package SportsPerfoemanceMonitoring.Backend.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
