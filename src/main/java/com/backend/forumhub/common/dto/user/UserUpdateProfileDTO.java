package com.backend.forumhub.common.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record UserUpdateProfileDTO(
        @Size(max = 150)
        String nome,

        @Email
        String email,

        @Pattern(regexp = "^\\d{10,15}$", message = "Telefone deve conter entre 10 e 15 dígitos numéricos")
        String telefone,

        @Size(min = 8)
        String senha
) {}

