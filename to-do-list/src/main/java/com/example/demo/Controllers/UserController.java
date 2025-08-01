package com.example.demo.Controllers;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/USER")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{user_id}")
    public User getTaskById(@PathVariable long user_id) {
        return userService.getUserById(user_id);
    }

    @PostMapping("/ADDUSER")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{user_id}/velocity")
    public int getUserVelocity(@PathVariable Long user_id) {
        return userService.getVelocityByUserId(user_id);
    }

}
