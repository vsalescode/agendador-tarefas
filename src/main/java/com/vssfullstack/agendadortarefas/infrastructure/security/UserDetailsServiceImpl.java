package com.vssfullstack.agendadortarefas.infrastructure.security;



import com.vssfullstack.agendadortarefas.business.dto.UsuarioDTO;
import com.vssfullstack.agendadortarefas.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;

    public UserDetails loadUserByUsername(String email, String token) {

        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email, token);

        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
