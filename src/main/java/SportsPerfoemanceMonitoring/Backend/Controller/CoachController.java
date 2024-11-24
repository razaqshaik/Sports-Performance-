package SportsPerfoemanceMonitoring.Backend.Controller;

import SportsPerfoemanceMonitoring.Backend.Dto.CoachDto;
import SportsPerfoemanceMonitoring.Backend.Model.Coach;
import SportsPerfoemanceMonitoring.Backend.Service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/coach")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity<?> updateProfile(@RequestBody CoachDto coachDto) {
        try {
            logCurrentUser();
            Coach updatedCoach = coachService.updateCoachProfile(coachDto);
            return ResponseEntity.ok(updatedCoach);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('COACH')")
    public ResponseEntity <?> viewProfile(@RequestParam String username) {
        try {
            logCurrentUser();
            Coach coach = coachService.viewProfile(username);
            return ResponseEntity.ok(coach);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    private void logCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
            System.out.println("Logged in user: " + username + ", Authorities: " + authorities);
        } else {
            String username = principal.toString();
            System.out.println("Logged in user: " + username);
        }
    }
}