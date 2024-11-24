package SportsPerfoemanceMonitoring.Backend.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
@Entity
@Table(name = "athlete")
@Data

public class Athlete extends Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athleteId;

    @ManyToOne
    @JoinColumn(name = "coachId", insertable = false, updatable = false)
    private Coach coach;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;
    private float height;
    private float weight;
    private String category;
    private String photoUrl;


}
