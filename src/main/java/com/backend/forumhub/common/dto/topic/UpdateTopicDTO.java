package com.backend.forumhub.common.dto.topic;

public record UpdateTopicDTO(
        String titulo,
        String mensagem,
        String status
) {}

