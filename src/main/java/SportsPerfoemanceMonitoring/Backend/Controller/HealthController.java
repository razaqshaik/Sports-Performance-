package SportsPerfoemanceMonitoring.Backend.Controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
