package com.example.demo.Controllers;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getTaskById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }


    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{user_id}/velocity")
    public int getUserVelocity(@PathVariable("user_id") Long user_id) {
        return userService.getVelocityByUserId(user_id);
    }

}
