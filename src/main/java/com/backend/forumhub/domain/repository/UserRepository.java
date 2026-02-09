package com.backend.forumhub.domain.repository;

import com.backend.forumhub.domain.model.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByEmail(@NotBlank @Email String email);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByIdAndIsActiveTrue(UUID id);
}
