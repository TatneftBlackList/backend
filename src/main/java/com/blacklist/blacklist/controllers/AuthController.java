package com.blacklist.blacklist.controllers;

import com.blacklist.blacklist.database.repository.AuthRepository;
import com.blacklist.blacklist.database.repository.RolesRepository;
import com.blacklist.blacklist.models.dto.AuthRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Tag(name = "Авторизация")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private RolesRepository rolesRepository;


    @PostMapping("/login")
    @Operation(summary = "Аутентификация пользователя")
    public ResponseEntity<?> authenticateUser(@RequestParam String login, @RequestParam String password) {
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequestDTO authRequestDTO) {

    }
}
