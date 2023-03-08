package com.example.restaurant_plaza.infrastructure.input.rest;

import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.dto.response.UserResponseDto;
import com.example.restaurant_plaza.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping("/createUser")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestDto userRequestDto){
        userHandler.saveUser(userRequestDto, 2L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUsers());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<UserResponseDto> getUserByDni(@PathVariable(name = "dni") String dni) {
        return ResponseEntity.ok(userHandler.getUserByDni(dni));
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserRequestDto userRequestDto) {
        userHandler.updateUser(userRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteUserByDni(@PathVariable String dni) {
        userHandler.deleteUserByDni(dni);
        return ResponseEntity.noContent().build();
    }
}
