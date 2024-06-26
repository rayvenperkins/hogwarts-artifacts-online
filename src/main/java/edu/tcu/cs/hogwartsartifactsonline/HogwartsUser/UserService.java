package edu.tcu.cs.hogwartsartifactsonline.HogwartsUser;

import edu.tcu.cs.hogwartsartifactsonline.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;


    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<HogwartsUser> findAll() {
        return this.userRepository.findAll();
    }

    public HogwartsUser findById(Integer userId) {
        Optional<HogwartsUser> optionalHogwartsUser = this.userRepository.findById(userId);
        if(optionalHogwartsUser.isPresent()) {
            return optionalHogwartsUser.get();
        } else {
            return null;
        }

    }

    public HogwartsUser save(HogwartsUser newHogwartsUser) {
        newHogwartsUser.setPassword(this.encoder.encode(newHogwartsUser.getPassword())); // Encode plain password before saving to the DB
        return this.userRepository.save(newHogwartsUser);
    }

    /**
     * We are not using this update to change user password.
     *
     * @param userId
     * @param update
     * @return
     */
    public HogwartsUser update(Integer userId, HogwartsUser update) {
        Optional<HogwartsUser> optionalHogwartsUser = this.userRepository.findById(userId);

        if(optionalHogwartsUser.isPresent()) {
            HogwartsUser oldHogwartsUser = optionalHogwartsUser.get();
            oldHogwartsUser.setUsername(update.getUsername());
            oldHogwartsUser.setEnabled(update.isEnabled());
            oldHogwartsUser.setRoles(update.getRoles());
            return this.userRepository.save(oldHogwartsUser);

        } else {
            return null;
        }

    }

    public void delete(Integer userId) {
        Optional<HogwartsUser> optionalHogwartsUser = this.userRepository.findById(userId);
        if(optionalHogwartsUser.isPresent()) {
            this.userRepository.deleteById(userId);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username) // First, we need to find this user from database.
                .map(MyUserPrincipal::new) // If found, wrap the returned user instance in a MyUserPrincipal instance.
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found.")); // Otherwise, throw an exception.
    }

}
