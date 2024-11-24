package SportsPerfoemanceMonitoring.Backend.Controller;

import SportsPerfoemanceMonitoring.Backend.Dto.AthleteDto;
import SportsPerfoemanceMonitoring.Backend.Model.Athlete;
import SportsPerfoemanceMonitoring.Backend.Service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/athlete")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ATHLETE')")
    public ResponseEntity<?> updateProfile(@RequestBody AthleteDto athleteDto) {
        try {
            Athlete updatedAthlete = athleteService.updateProfile(athleteDto);
            return ResponseEntity.ok(updatedAthlete);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('ATHLETE')")
    public ResponseEntity<?> viewProfile(@RequestParam String username) {
        try {
            Athlete athlete = athleteService.viewProfile(username);
            return ResponseEntity.ok(athlete);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}