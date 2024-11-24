package SportsPerfoemanceMonitoring.Backend.Service;

import SportsPerfoemanceMonitoring.Backend.Dto.AthleteDto;
import SportsPerfoemanceMonitoring.Backend.Model.Athlete;
import SportsPerfoemanceMonitoring.Backend.Repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Athlete updateProfile(AthleteDto athleteDto) {
        Athlete athlete = athleteRepository.findByUsername(athleteDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Athlete not found"));
        athlete.setFirstName(athleteDto.getFirstName());
        athlete.setLastName(athleteDto.getLastName());
        athlete.setBirthDate(athleteDto.getBirthDate());
        athlete.setGender(athleteDto.getGender());
        athlete.setHeight(athleteDto.getHeight());
        athlete.setWeight(athleteDto.getWeight());
        athlete.setCategory(athleteDto.getCategory());
        athlete.setPhotoUrl(athleteDto.getPhotoUrl());
        return athleteRepository.save(athlete);
    }

    public Athlete viewProfile(String username) {
        return athleteRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Athlete not found"));
    }
}