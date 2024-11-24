package SportsPerfoemanceMonitoring.Backend.Controller;

import SportsPerfoemanceMonitoring.Backend.Dto.UsersDto;
import SportsPerfoemanceMonitoring.Backend.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsersDto userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersDto userDTO) {
        try {
            boolean isAuthenticated = userService.authenticateUser(userDTO);
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred during login: " + e.getMessage());
        }
    }
}