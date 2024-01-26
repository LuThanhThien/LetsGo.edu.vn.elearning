package letsgo.vn.elearning.controller.admin;

import letsgo.vn.elearning.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    @GetMapping("/home")
    public ResponseEntity<String> userHome() {
        return ResponseEntity.ok("Welcome to dashboard");
    }
}
