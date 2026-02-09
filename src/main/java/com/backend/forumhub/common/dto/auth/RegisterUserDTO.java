package com.backend.forumhub.common.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank
        @Size(max = 150)
        String nome,
        @NotBlank
        @Email
        String email,
        @Pattern(
                regexp = "^\\d{10,15}$",
                message = "Telefone deve conter entre 10 e 15 dígitos numéricos"
        )
        String telefone,
        @NotBlank
        @Size(min = 8)
        String senha
) {
}
