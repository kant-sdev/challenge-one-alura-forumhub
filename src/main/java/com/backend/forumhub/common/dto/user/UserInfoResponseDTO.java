package com.backend.forumhub.common.dto.user;

import com.backend.forumhub.domain.model.UserModel;

import java.util.UUID;

public record UserInfoResponseDTO(
        String nome,
        String email,
        String telefone,
        Boolean status
){
    public UserInfoResponseDTO(UserModel user) {
        this(user.getNome(), user.getEmail(), user.getTelefone(),  user.getActive());
    }


    public static UserInfoResponseDTO from(UserModel autor) {
        return new UserInfoResponseDTO(autor);
    }
}
