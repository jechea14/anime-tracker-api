package com.example.animetracker.service;

import com.example.animetracker.model.ApplicationUser;
import com.example.animetracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "postgresUserService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<ApplicationUser> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public void updateUser(Integer id, ApplicationUser applicationUser) {
        Optional<ApplicationUser> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.save(applicationUser);
    }

    public void deleteUser(Integer id) {
        doesUserExist(id);

        userRepository.deleteById(id);
    }

    private void doesUserExist(Integer userId) {
        Optional<ApplicationUser> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

//        if(!username.equals("Ethan")) throw new UsernameNotFoundException("Ethan not found");
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1, "USER"));
//
//        return new ApplicationUser(1, "Ethan", encoder.encode("password"), roles);
    }
}
