package com.example.education.controller;

import com.example.education.config.MyUserDetails;
import com.example.education.dao.LoginForm;
import com.example.education.model.User;
import com.example.education.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/test/authTest")
    public String authTest() {
        return "authtest";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginForm form,
                             HttpServletRequest request) throws ServletException {

        try {
            request.login(form.mail, form.password);
        } catch (ServletException e) {
            throw new RuntimeException("Invalid username or password");
        }

        var auth = (Authentication) request.getUserPrincipal();
        var user = (MyUserDetails) auth.getPrincipal();

        return "Пользователь: " + user.getUsername();
    }

    @GetMapping("/current-user")
    public String getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            return ((MyUserDetails) principal).getUsername();
        }

        return "Не авторизован";
    }

    @PostMapping("/registration")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);

        return "User is saved";
    }
}
