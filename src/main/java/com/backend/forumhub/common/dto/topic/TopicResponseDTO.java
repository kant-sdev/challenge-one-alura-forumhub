package com.backend.forumhub.common.dto.topic;

import com.backend.forumhub.common.dto.user.UserInfoResponseDTO;
import com.backend.forumhub.domain.model.TopicModel;

import java.time.LocalDateTime;
public record TopicResponseDTO(
        Long id,
        String titulo,
        String mensagem,
        String status,
        String tipoForum,
        String tipoTopico,
        String curso,
        Boolean deleted,
        LocalDateTime dataCriacao,
        UserInfoResponseDTO autor
) {

    public TopicResponseDTO(TopicModel topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getStatus().name(),
                topico.getTipoForum().name(),
                topico.getTipoTopico().name(),
                topico.getCurso() != null ? topico.getCurso() : null,
                topico.getDeleted(),
                topico.getDataCriacao(),
                UserInfoResponseDTO.from(topico.getAutor())
        );
    }

    public static TopicResponseDTO from(TopicModel topico) {
        return new TopicResponseDTO(topico);
    }
}

