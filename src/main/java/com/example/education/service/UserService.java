package com.example.education.service;

import com.example.education.model.User;
import com.example.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(String mail) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        return user;
    }
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void completeTask1(String mail) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        user.setTask1Completed(true);
        userRepository.save(user);
    }

    public void addMistakeToTask1(String mail) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        user.setTask1Mistakes(user.getTask1Mistakes() + 1);
        userRepository.save(user);
    }

    public void completeTask2(String mail) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        user.setTask2Completed(true);
        userRepository.save(user);
    }

    public void addMistakeToTask2(String mail) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        user.setTask2Mistakes(user.getTask2Mistakes() + 1);
        userRepository.save(user);
    }

    public void completeTask3(String mail) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        user.setTask3Completed(true);
        userRepository.save(user);
    }

    public void addMistakeToTask3(String mail) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        user.setTask3Mistakes(user.getTask3Mistakes() + 1);
        userRepository.save(user);
    }

    public void completeTask4(String mail, int mistakesCount) {
        User user = userRepository.findUserByMailIs(mail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with mail: " + mail + " not found"))
                ;
        user.setTask4Mistakes(mistakesCount);
        user.setTask4Completed(true);
        userRepository.save(user);
    }
}
