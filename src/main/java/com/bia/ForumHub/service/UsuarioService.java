package com.bia.ForumHub.service;

import com.bia.ForumHub.dto.RequestUsuario;
import com.bia.ForumHub.model.Usuario;
import com.bia.ForumHub.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void createUser(RequestUsuario dados){
        var usuario = new Usuario(
                null,
                dados.nome(),
                dados.email(),
                dados.senha(),
                List.of()
        );
        usuarioRepositorio.save(usuario);
    }
}
