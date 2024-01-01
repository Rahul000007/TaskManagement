package manage.task.taskmanagement.controller;

import manage.task.taskmanagement.model.User;
import manage.task.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signUp";
    }
    @PostMapping("/do-signUp")
    public String doSignUp(@RequestParam String email,@RequestParam String password,@RequestParam String name) {
        User user =new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ADMIN");
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

}
