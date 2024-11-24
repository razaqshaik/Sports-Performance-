
package SportsPerfoemanceMonitoring.Backend.Service;
import SportsPerfoemanceMonitoring.Backend.Model.Users;
import SportsPerfoemanceMonitoring.Backend.Model.Admin;
import SportsPerfoemanceMonitoring.Backend.Model.Athlete;
import SportsPerfoemanceMonitoring.Backend.Model.Coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    @Lazy
    private UsersService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof Athlete) {
            authorities.add(new SimpleGrantedAuthority("ATHLETE"));
        } else if (user instanceof Coach) {
            authorities.add(new SimpleGrantedAuthority("COACH"));
        } else if (user instanceof Admin) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        // Add logging
        System.out.println("User: " + user.getUsername() + ", Authorities: " + authorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}