package com.bia.ForumHub.service;

import com.bia.ForumHub.dto.RequestUsuario;
import com.bia.ForumHub.model.Usuario;
import com.bia.ForumHub.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(RequestUsuario dados){
        var usuario = new Usuario(
                null,
                dados.nome(),
                dados.email(),
                passwordEncoder.encode(dados.senha()),
                List.of()
        );
        usuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositorio.findByEmail(username);
    }
}
