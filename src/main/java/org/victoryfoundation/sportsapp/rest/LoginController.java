package org.victoryfoundation.sportsapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.victoryfoundation.sportsapp.dao.UserRepository;
import org.victoryfoundation.sportsapp.domains.LoginRequest;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>("Invalid username or credentials", HttpStatus.UNAUTHORIZED);
    }
}
