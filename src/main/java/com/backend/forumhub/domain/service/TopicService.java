package com.backend.forumhub.domain.service;

import com.backend.forumhub.common.dto.topic.CreateTopicDTO;
import com.backend.forumhub.common.dto.topic.TopicListResponseDTO;
import com.backend.forumhub.common.dto.topic.TopicResponseDTO;
import com.backend.forumhub.common.dto.topic.UpdateTopicDTO;
import com.backend.forumhub.domain.exception.BusinessException;
import com.backend.forumhub.domain.model.TopicModel;
import com.backend.forumhub.domain.model.UserModel;
import com.backend.forumhub.domain.model.enums.StatusTopico;
import com.backend.forumhub.domain.repository.TopicRepository;
import com.backend.forumhub.domain.validations.TopicValidator;
import com.backend.forumhub.infra.security.UserPrincipal;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicValidator validator;


    public TopicService(TopicValidator validator,
                        TopicRepository topicRepository) {
        this.validator = validator;
        this.topicRepository = topicRepository;
    }

    @Transactional
    public TopicModel criarTopico(CreateTopicDTO dto) {

        UserModel autor = getUsuarioAutenticado();

        validator.validarCriacao(dto, autor);

        TopicModel topico = new TopicModel(
                dto.titulo(),
                dto.mensagem(),
                autor,
                dto.tipoForum(),
                dto.curso(),
                dto.tipoTopico()
        );

        return topicRepository.save(topico);
    }

    public List<TopicModel> listarTopicosNaoDeletados() {
        return topicRepository.findAllByDeletedFalse();

    }

    @Transactional
    public void softDeleteTopico(Long id) {
        TopicModel topico = topicRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Tópico não encontrado"));

        topico.setDeleted(true);
    }

    @Transactional
    public TopicModel atualizarTopico(
            Long id,
            UpdateTopicDTO dto,
            Authentication authentication
    ) {
        TopicModel topico = topicRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BusinessException("Tópico não encontrado"));

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        UserModel usuario = principal.getUser();

        if (!topico.getAutor().getId().equals(usuario.getId())) {
            throw new BusinessException("Você não pode editar este tópico");
        }

        if (dto.titulo() != null) {
            topico.setTitulo(dto.titulo());
        }

        if (dto.mensagem() != null) {
            topico.setMensagem(dto.mensagem());
        }

        if (dto.status() != null) {
            topico.setStatus(StatusTopico.valueOf(dto.status()));
        }

        return topico;
    }

    private UserModel getUsuarioAutenticado() {

        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        return principal.getUser();
    }
}
