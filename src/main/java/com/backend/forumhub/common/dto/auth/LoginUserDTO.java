package com.backend.forumhub.common.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record LoginUserDTO(
    @Email
    String email,
    @Size(min = 8)
    String senha

) {
}
