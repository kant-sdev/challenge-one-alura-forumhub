package com.backend.forumhub.api.controller;


import com.backend.forumhub.common.dto.topic.CreateTopicDTO;
import com.backend.forumhub.common.dto.topic.TopicListResponseDTO;
import com.backend.forumhub.common.dto.topic.TopicResponseDTO;
import com.backend.forumhub.common.dto.topic.UpdateTopicDTO;
import com.backend.forumhub.domain.model.TopicModel;
import com.backend.forumhub.domain.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> criar(@RequestBody CreateTopicDTO dto) {
        TopicModel topic = service.criarTopico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TopicResponseDTO(topic));
    }

    @GetMapping
    public ResponseEntity<List<TopicListResponseDTO>> listar() {

        List<TopicListResponseDTO> response = service
                .listarTopicosNaoDeletados()
                .stream()
                .map(TopicListResponseDTO::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody UpdateTopicDTO dto,
            Authentication authentication
    ) {
        TopicModel atualizado = service.atualizarTopico(id, dto, authentication);
        return ResponseEntity.ok(new TopicResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        service.softDeleteTopico(id);
        return ResponseEntity.ok(
                Map.of("message", "Tópico removido com sucesso")
        );
    }

}
