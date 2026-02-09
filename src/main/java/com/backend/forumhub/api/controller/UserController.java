package com.backend.forumhub.api.controller;

import com.backend.forumhub.common.dto.user.UserInfoResponseDTO;
import com.backend.forumhub.common.dto.auth.RegisterResponse;
import com.backend.forumhub.common.dto.user.UserUpdateProfileDTO;
import com.backend.forumhub.domain.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponseDTO> getDataUser(@PathVariable UUID id){
        var user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<RegisterResponse> updateProfileUser(
            @PathVariable UUID id,
            @RequestBody @Valid UserUpdateProfileDTO dto
    ) {
        var user = userService.updateProfile(id, dto);
        return ResponseEntity.ok(new RegisterResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deactivateAccount(@PathVariable UUID id) {
        userService.deactivateAccount(id);
        Map<String, Object> response = Map.of(
                "id", id,
                "status", "deactivated"
        );
        return ResponseEntity.ok(response);
    }

}