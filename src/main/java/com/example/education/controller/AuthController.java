package com.example.education.controller;

import com.example.education.config.MyUserDetails;
import com.example.education.dao.LoginForm;
import com.example.education.dao.UserInfo;
import com.example.education.model.User;
import com.example.education.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

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
    public UserInfo login(@RequestBody LoginForm form,
                             HttpServletRequest request) throws ServletException {

        request.logout();

        try {
            request.login(form.mail, form.password);
        } catch (ServletException e) {
            throw new RuntimeException("Invalid username or password");
        }

        var auth = (Authentication) request.getUserPrincipal();

        User user = userService.getUser(((MyUserDetails) auth.getPrincipal()).getUsername());
        return UserInfo.fromUser(user);
    }

    @GetMapping("/current-user")
    public UserInfo getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            User user = userService.getUser(((MyUserDetails) principal).getUsername());
            return UserInfo.fromUser(user);
        }

        throw new UsernameNotFoundException("Пользователь не авторизован");
    }

    @PostMapping("/registration")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);

        return "User is saved";
    }
}
