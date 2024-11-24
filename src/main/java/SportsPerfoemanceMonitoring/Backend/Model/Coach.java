package SportsPerfoemanceMonitoring.Backend.Model;

import org.springframework.security.core.userdetails.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "coach")
@Data

public class Coach extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "coach")
    private List<Athlete> athletes;



}
