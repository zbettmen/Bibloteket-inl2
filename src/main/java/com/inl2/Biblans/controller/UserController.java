package com.inl2.Biblans.controller;


import com.inl2.Biblans.entities.User;
import com.inl2.Biblans.services.BookService;
import com.inl2.Biblans.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @PostMapping("/start")
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String name){
        return ResponseEntity.ok(userService.findAll(name));
    }
}
