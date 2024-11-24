package SportsPerfoemanceMonitoring.Backend.Repository;

import SportsPerfoemanceMonitoring.Backend.Model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository <Coach , Long> {
    Optional<Coach> findByUsername(String username);
}
