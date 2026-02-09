package com.backend.forumhub.api.controller;

import com.backend.forumhub.common.dto.ApiStatusResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/check-api")
public class CheckController {

    @GetMapping
    public ResponseEntity<ApiStatusResponseDTO> checkApi() {
        return ResponseEntity.ok(
                new ApiStatusResponseDTO(
                        "UP",
                        "API funcionando corretamente",
                        LocalDateTime.now()
                )
        );
    }
}
