package sda.mytrip.mytrip.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import sda.mytrip.mytrip.user.UserAccount;
import sda.mytrip.mytrip.user.UserAccountService;


@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserAccountService userService;

    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserAccount user) {
        userService.register(user);

        String token = authService.createAuthToken(user.getEmail());
        AuthResponse authResponse = new AuthResponse(token);

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            String token = authService.authenticate(authRequest.getEmail(), authRequest.getPassword());
            AuthResponse authResponse = new AuthResponse(token);

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (AuthenticationException authenticationException) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path="/profile")
    public UserAccount profile() {
        UserAccount user = authService.getUserProfile();
        return user;
    }

    @PutMapping(path = "/profile",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserProfile(@RequestBody UserAccount user) {
        authService.updateUserProfile(user);
        return new ResponseEntity<>("User profile updated!", HttpStatus.CREATED);
    }

    @GetMapping(path="/username")
    public String username() {
        return authService.getLoggedInUserEmail();
    }
}
