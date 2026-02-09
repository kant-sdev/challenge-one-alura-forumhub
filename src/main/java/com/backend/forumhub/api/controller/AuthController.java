package com.backend.forumhub.api.controller;


import com.backend.forumhub.common.dto.auth.LoginResponseDTO;
import com.backend.forumhub.common.dto.auth.LoginUserDTO;
import com.backend.forumhub.common.dto.auth.RegisterUserDTO;
import com.backend.forumhub.common.dto.auth.RegisterResponse;
import com.backend.forumhub.domain.service.AuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<RegisterResponse> cadastrarUsuario(
            @RequestBody @Valid RegisterUserDTO req,
            UriComponentsBuilder uriBuilder
    ) {

        var usuario = authService.cadastrarUsuario(req);
        var uri = uriBuilder.path("/auth/register/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginUserDTO req) {

        String token = authService.logarUsuario(req);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
