package com.example.demo.Service;

import com.example.demo.Model.Task;
import com.example.demo.Model.User;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            int calculatedVelocity = getVelocityByUserId(user.getId());
            user.setVelocity(calculatedVelocity);
        }
        return users;
    }


    public User getUserById(long uId) {
        User user = userRepository.findById(uId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int calculatedVelocity = getVelocityByUserId(user.getId());
        user.setVelocity(calculatedVelocity);

        return user;
    }



    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username '" + user.getUsername() + "' is already taken.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }


    public void updateUser(User user)
    {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username '" + user.getUsername() + "' is already taken.");
        }
        userRepository.save(user);
    }

    public void deleteTaskById(Long uId)
    {
        userRepository.deleteById(uId);
    }

    public int getVelocityByUserId(Long userId) {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return taskRepository
                .findByUserIdAndStatusAndEndTimeAfter(userId, Status.COMPLETED, thirtyDaysAgo)
                .size();
    }



}
