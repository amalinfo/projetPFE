package com.example.demo.authentication;
import com.example.demo.config.JwtService;
import com.example.demo.dto.ForgetPassword;
import com.example.demo.entites.*;
import com.example.demo.repositories.TokenRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.serviceImpl.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public ResponseEntity<?> register(User request) throws MessagingException {
        var user = User.builder()
                .nom(request.getNom())
                .adress(request.getAdress())
                .email(request.getEmail())
                .prenom(request.getPrenom())
                .age(request.getAge())
                .numTel(request.getNumTel())
                .role(request.getRole()==null?Role.USER:request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var savedUser = repository.save(user);
        emailService.sendMailwithtml(request.getEmail(),"new authentication details ",
                "<html><h1> your new credentials </h1> </br> <ul><li>email : "
                        +request.getEmail() + " </li><li>password : "
                        +request.getPassword() + " </li></ul></htm>");

      //  var jwtToken = jwtService.generateToken(user);
        //saveUserToken(savedUser, jwtToken);
       /* return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();*/
        return ResponseEntity.ok(savedUser);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .admin(user.getRole().name())
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user);
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    private String generate(){
        String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            char character = ALPHA_NUMERIC_STRING.charAt(index);
            builder.append(character);
        }
        return builder.toString();
    }
    public ResponseEntity<?> forgetPassword(ForgetPassword request) {
     User user=repository.findByEmail(request.getEmail()).get();
        if(user!=null){
            String pwt=generate();
            BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(pwt));
          repository.save(user);
            emailService.sendMail(user.getEmail(),"new password", pwt);
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.ok("User not found");
    }
}
