package SportsPerfoemanceMonitoring.Backend.Model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class Admin extends Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String name;
    // Additional fields specific to Admins if needed

}
