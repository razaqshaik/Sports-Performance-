package SportsPerfoemanceMonitoring.Backend.Service;

import SportsPerfoemanceMonitoring.Backend.Dto.UsersDto;
import SportsPerfoemanceMonitoring.Backend.Model.Users;
import SportsPerfoemanceMonitoring.Backend.Model.Admin;
import SportsPerfoemanceMonitoring.Backend.Model.Athlete;
import SportsPerfoemanceMonitoring.Backend.Model.Coach;
import SportsPerfoemanceMonitoring.Backend.Repository.AdminRepository;
import SportsPerfoemanceMonitoring.Backend.Repository.AthleteRepository;
import SportsPerfoemanceMonitoring.Backend.Repository.CoachRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        if (adminRepository.findByUsername("admin").isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            adminRepository.save(admin);
        }
    }

    public void registerUser(UsersDto userDTO) {
        switch (userDTO.getRole().toLowerCase()) {
            case "athlete":
                if (athleteRepository.findByUsername(userDTO.getUsername()).isPresent()) {
                    throw new IllegalArgumentException("Username already exists");
                }
                Athlete athlete = new Athlete();
                athlete.setUsername(userDTO.getUsername());
                athlete.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                athleteRepository.save(athlete);
                break;

            case "coach":
                if (coachRepository.findByUsername(userDTO.getUsername()).isPresent()) {
                    throw new IllegalArgumentException("Username already exists");
                }
                Coach coach = new Coach();
                coach.setUsername(userDTO.getUsername());
                coach.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                coachRepository.save(coach);
                break;

            case "admin":
                throw new IllegalArgumentException("Admin registration is not allowed via this method.");
            default:
                throw new IllegalArgumentException("Invalid role: " + userDTO.getRole());
        }
    }

    public Users findByUsername(String username) {
        Optional<Athlete> athleteByUsername = athleteRepository.findByUsername(username);
        if (athleteByUsername.isPresent()) return athleteByUsername.get();

        Optional<Coach> coachByUsername = coachRepository.findByUsername(username);
        if (coachByUsername.isPresent()) return coachByUsername.get();

        Optional<Admin> adminByUsername = adminRepository.findByUsername(username);
        if (adminByUsername.isPresent()) return adminByUsername.get();

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public boolean checkPassword(String rawPassword, String storedPassword) {
        return passwordEncoder.matches(rawPassword, storedPassword);
    }

    public boolean authenticateUser(UsersDto userDTO) {
        Users user = findByUsername(userDTO.getUsername());
        if (user == null) {
            System.out.println("User not found");
            return false;
        }
        System.out.println("Raw password: " + userDTO.getPassword());
        System.out.println("Stored password: " + user.getPassword());
        boolean passwordMatches = checkPassword(userDTO.getPassword(), user.getPassword());
        if (!passwordMatches) {
            System.out.println("Password does not match");
        }
        return passwordMatches;
    }
}