package com.example.demo.serviceImpl;

import  com.example.demo.entites.Champ;
import com.example.demo.entites.User;
import com.example.demo.repositories.ChampRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ChampRepository champRepository;

    @Override
    public ResponseEntity<?> delete(Long id) {
        User user=userRepository.findById(id).get();
        for(Champ c:user.getChamp()){
            c.setUser(null);
            champRepository.save(c);

        }
        userRepository.deleteById(id);
        return ResponseEntity.status(200).body("delete");
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.status(200).body(user.get());

        } else
            return ResponseEntity.status(200).body("User Not Found");
    }

    @Override
    public ResponseEntity<?> findAllAppUser() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }


    @Override
    public ResponseEntity<?> modifier(Long id, User user) {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isPresent()) {
            User up = user1.get();
            up.setEmail(user.getEmail());
            up.setAge(user.getAge());
            up.setNom(user.getNom());
            up.setPrenom(user.getPrenom());
            up.setNumTel(user.getNumTel());
            up.setRole(up.getRole());
            userRepository.save(up);
            return ResponseEntity.ok(up);
        } else {
            return ResponseEntity.ok("user not found");
        }
    }
}


