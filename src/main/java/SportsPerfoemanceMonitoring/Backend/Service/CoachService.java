package SportsPerfoemanceMonitoring.Backend.Service;

import SportsPerfoemanceMonitoring.Backend.Dto.CoachDto;
import SportsPerfoemanceMonitoring.Backend.Model.Coach;
import SportsPerfoemanceMonitoring.Backend.Repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Coach updateCoachProfile(CoachDto coachDto) {
        Coach coach = coachRepository.findByUsername(coachDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());
        return coachRepository.save(coach);
    }

    public Coach viewProfile(String username) {
        return coachRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found"));
    }
}
