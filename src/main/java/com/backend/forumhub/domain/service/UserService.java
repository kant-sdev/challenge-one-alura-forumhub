

package com.backend.forumhub.domain.service;

import com.backend.forumhub.common.dto.user.UserInfoResponseDTO;
import com.backend.forumhub.common.dto.user.UserUpdateProfileDTO;
import com.backend.forumhub.domain.exception.BusinessException;
import com.backend.forumhub.domain.model.UserModel;
import com.backend.forumhub.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserInfoResponseDTO getUserById(UUID id) {
        UserModel user = userRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        return new UserInfoResponseDTO(user);
    }

    @Transactional
    public UserModel updateProfile(UUID id, UserUpdateProfileDTO dto) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (dto.nome() != null) user.setNome(dto.nome());
        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.telefone() != null) user.setTelefone(dto.telefone());
        if (dto.senha() != null) user.setSenha(passwordEncoder.encode(dto.senha()));

        return userRepository.save(user);
    }

    public void deactivateAccount(UUID id) {
        UserModel user = userRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        user.deactive();

        userRepository.save(user);
    }

}
