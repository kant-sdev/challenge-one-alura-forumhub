package com.backend.forumhub.common.dto.topic;

import com.backend.forumhub.domain.model.TopicModel;

import java.time.LocalDateTime;

public record TopicListResponseDTO(
        Long id,
        String titulo,
        String mensagem,
        String status,
        String tipoForum,
        String tipoTopico,
        String curso,
        Boolean deleted,
        LocalDateTime dataCriacao,
        String autor
) {
    public static TopicListResponseDTO from(TopicModel t) {
        return new TopicListResponseDTO(
                t.getId(),
                t.getTitulo(),
                t.getMensagem(),
                t.getStatus().name(),
                t.getTipoForum().name(),
                t.getTipoTopico().name(),
                t.getCurso(),
                t.getDeleted(),
                t.getDataCriacao(),
                t.getAutor().getNome()
        );
    }
}
