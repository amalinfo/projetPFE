package com.example.demo.authentication;

import com.example.demo.dto.ForgetPassword;
import com.example.demo.entites.User;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin( origins = "http://localhost:4200/",allowedHeaders = "*")
@RequiredArgsConstructor

public class AuthenticationContrller {
    private  final AuthenticationService authenticationService;
    @CrossOrigin( origins = "http://localhost:4200/",allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<?> authentciate(@RequestBody AuthenticationRequest request){
        return  ResponseEntity.ok( authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws MessagingException {
        return authenticationService.register(user);
    }
    @PostMapping("/password")
    public ResponseEntity<?>forgetPassword(@RequestBody() ForgetPassword email){
        return authenticationService.forgetPassword(email);
    }

}
