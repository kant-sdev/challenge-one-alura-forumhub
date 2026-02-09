package com.backend.forumhub.common.dto;

import java.time.LocalDateTime;

public record ApiStatusResponseDTO(
        String status,
        String message,
        LocalDateTime timestamp
) {}
