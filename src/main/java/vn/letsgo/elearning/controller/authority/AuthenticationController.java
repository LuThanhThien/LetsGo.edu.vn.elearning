package vn.letsgo.elearning.controller.authority;

import vn.letsgo.elearning.dto.user.authentication.LoginDto;
import vn.letsgo.elearning.dto.user.authentication.RegisterDto;
import vn.letsgo.elearning.security.AuthenticationResponse;
import vn.letsgo.elearning.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final AuthenticationService authService;

    @GetMapping("/register")
    public ResponseEntity<String> registerForm() {
        return ResponseEntity.ok("On register page");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("On login page");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }


}
