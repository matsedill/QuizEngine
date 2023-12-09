package engine;

import engine.dto.UserInput;
import engine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserInput userInput) {
        userService.registerUser(userInput);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
