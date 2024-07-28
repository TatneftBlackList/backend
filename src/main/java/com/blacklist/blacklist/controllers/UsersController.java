package com.blacklist.blacklist.controllers;

import com.blacklist.blacklist.models.dto.UsersDTO;
import com.blacklist.blacklist.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name="CRUD по пользователям")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    @Operation(summary = "Возвращает список пользователей")
    public ResponseEntity<List<UsersDTO>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/users/{job_number}")
    @Operation(summary = "Возвращает пользователя по ID")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable("job_number") String job_number) {
        return ResponseEntity.ok(usersService.getUserByJobNumber(job_number));
    }

    @PostMapping("/users")
    @Operation(summary = "Добавление пользователя")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO userDTO) {
        UsersDTO usersDTO = usersService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersDTO);
    }
}

