package com.backend.forumhub.common.dto.topic;

import com.backend.forumhub.domain.model.enums.TipoForum;
import com.backend.forumhub.domain.model.enums.TipoTopico;

public record CreateTopicDTO (
    String titulo,
    String mensagem,
    TipoForum tipoForum,
    String curso,
    TipoTopico tipoTopico
) {}