package vn.letsgo.elearning.controller.user;

import vn.letsgo.elearning.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public ResponseEntity<String> userHome() {
        return ResponseEntity.ok("Welcome home");
    }



}
