package com.backend.forumhub.domain.validations;

import com.backend.forumhub.common.dto.topic.CreateTopicDTO;
import com.backend.forumhub.domain.exception.BusinessException;
import com.backend.forumhub.domain.model.UserModel;
import com.backend.forumhub.domain.model.enums.TipoForum;
import org.springframework.stereotype.Component;

@Component
public class TopicValidator {

    public void validarCriacao(CreateTopicDTO dto, UserModel autor) {

        validarConteudo(dto);
        validarContextoForum(dto);
        validarAutor(autor);
    }

    private void validarConteudo(CreateTopicDTO dto) {
        if (dto.titulo() == null || dto.titulo().isBlank()) {
            throw new BusinessException("Título é obrigatório");
        }
        if (dto.mensagem() == null || dto.mensagem().isBlank()) {
            throw new BusinessException("Mensagem é obrigatória");
        }
    }

    private void validarContextoForum(CreateTopicDTO dto) {
        if (dto.tipoForum() == TipoForum.CURSO && dto.curso() == null) {
            throw new BusinessException("Curso obrigatório para fórum de curso");
        }
        if (dto.tipoForum() == TipoForum.ABERTO && dto.curso() != null) {
            throw new BusinessException("Curso não permitido em fórum aberto");
        }
    }

    private void validarAutor(UserModel autor) {
        if (!autor.getActive()) {
            throw new BusinessException("Usuário inativo não pode criar tópicos");
        }
    }
}