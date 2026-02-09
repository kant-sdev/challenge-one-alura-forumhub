package com.backend.forumhub.domain.service;


import com.backend.forumhub.common.dto.auth.LoginUserDTO;
import com.backend.forumhub.common.dto.auth.RegisterUserDTO;
import com.backend.forumhub.common.dto.auth.RegisterResponse;
import com.backend.forumhub.domain.model.UserModel;
import com.backend.forumhub.domain.repository.UserRepository;
import com.backend.forumhub.infra.security.TokenConfig;
import com.backend.forumhub.infra.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            TokenConfig tokenConfig
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    public RegisterResponse cadastrarUsuario(RegisterUserDTO dto){
        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw  new RuntimeException("Email já cadastrado");
        }

        UserModel usuario = new UserModel(
                dto.nome(),
                dto.email(),
                dto.telefone(),
                passwordEncoder.encode(dto.senha())
        );

        userRepository.save(usuario);

        return new RegisterResponse(
                usuario.getId(),
                usuario.getActive()
        );
    }

    public String logarUsuario(LoginUserDTO dados) {
        var authToken = new UsernamePasswordAuthenticationToken(
                dados.email(),
                dados.senha()
        );

        Authentication authentication =
                authenticationManager.authenticate(authToken);

        UserPrincipal principal =
                (UserPrincipal) authentication.getPrincipal();

        UserModel usuario = principal.getUser();

        return tokenConfig.gerarTokenJWT(usuario);
    }


}
