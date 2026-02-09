package com.backend.forumhub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.backend.forumhub.common.dto.auth.LoginUserDTO;
import com.backend.forumhub.domain.exception.BusinessException;
import com.backend.forumhub.domain.model.UserModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TokenConfig {

    private static final String ISSUER = "FORUMHUB API";
    private static final String SECRET = "wasd";

    public String gerarTokenJWT(UserModel usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getEmail())
                    .withClaim(
                            "roles",
                            List.of(usuario.getRole().name())
                    )
                    .withExpiresAt(dataValidToken())
                    .sign(algorithm);

        } catch (JWTCreationException ex) {
            throw new BusinessException("Erro ao gerar token JWT");
        }
    }

    private Instant dataValidToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

