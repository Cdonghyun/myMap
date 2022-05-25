package my.myMap.service;

import my.myMap.model.User;
import my.myMap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setEnabled(true);
         return userRepository.save(user);
    }
}
