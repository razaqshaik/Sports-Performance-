package SportsPerfoemanceMonitoring.Backend.Repository;
import SportsPerfoemanceMonitoring.Backend.Model.Athlete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface AthleteRepository extends JpaRepository <Athlete ,  Long>{
    Optional<Athlete> findByUsername(String username);
}
