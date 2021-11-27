package org.alkemy.challenge1.authentication;

import org.alkemy.challenge1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService usrSrv;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User usr) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usr.getEmail(), usr.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new TokenView(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> registerUser(@Valid @RequestBody User usr_unsecure) {
        if (usrSrv.emailExists(usr_unsecure.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: E-mail is already in use"));
        }


        // Create new user's account hashing the password.
        User usr = new User(usr_unsecure.getEmail(),
                encoder.encode(usr_unsecure.getPassword()));
        usrSrv.saveUser(usr);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
