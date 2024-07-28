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

    @GetMapping("/users/{jobNumber}")
    @Operation(summary = "Возвращает пользователя по ID")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable("jobNumber") String job_number) {
        return ResponseEntity.ok(usersService.getUserByJobNumber(job_number));
    }

    @PostMapping("/users")
    @Operation(summary = "Добавление пользователя")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO userDTO) {
        UsersDTO usersDTO = usersService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersDTO);
    }

    @DeleteMapping("/users/{jobNumber}")
    @Operation(summary = "Удаление пользователя по jobNumber")
    public ResponseEntity<String> deleteUser(@PathVariable("jobNumber") String job_number) {
        boolean deleteUsers = usersService.deleteUsersByJobNumber(job_number);
        if (deleteUsers) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Users not found");
        }
    }

}

