package com.backend.forumhub.common.dto.auth;

import com.backend.forumhub.domain.model.UserModel;

import java.util.UUID;

public record RegisterResponse(
        UUID id,
        Boolean status
) {

    public RegisterResponse(UserModel user) {
        this( user.getId(), user.getActive());
    }
}
