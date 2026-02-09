package com.backend.forumhub.infra.security;

import com.backend.forumhub.domain.model.UserModel;
import com.backend.forumhub.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceIpml implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceIpml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));

        return new UserPrincipal(user);
    }
}
