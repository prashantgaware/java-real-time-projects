package com.nt.controller;

import com.nt.dto.UserDto;
import com.nt.service.IUserRegistrationService;
import com.nt.service.implementation.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    private IUserRegistrationService userRegistrationService;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> allUsers = this.userRegistrationService.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto>create(@RequestBody UserDto userDto) {
        this.userRegistrationService.create(userDto);
        return ResponseEntity.ok().body(userDto);
    }
}
