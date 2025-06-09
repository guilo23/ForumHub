package com.bia.ForumHub.service;

import com.bia.ForumHub.dto.RequestPerfil;
import com.bia.ForumHub.model.Perfil;
import com.bia.ForumHub.repository.PerfilRepository;
import com.bia.ForumHub.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Perfil createPerfil(Long userId, RequestPerfil dados){
         var usuario = usuarioRepositorio.findById(userId)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        boolean jaTemPerfil = usuario.getPerfis().stream()
                .anyMatch(p -> p.getAuthority().equalsIgnoreCase(dados.authority()));
        if (jaTemPerfil) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já possui este perfil.");
        }
         var perfil = new Perfil(
                 null,
                 dados.nomePerfil(),
                 dados.authority(),
                 usuario
         );
         return perfilRepository.save(perfil);

    }
    public void PerfilUpdate(RequestPerfil dados, Long id){
        var perfil = perfilRepository.findById(id).orElseThrow();
        perfil.setNomeDoPerfil(dados.nomePerfil());
        perfilRepository.save(perfil);
    }
    public void PerfilDelete(Long id){
        perfilRepository.deleteById(id);
    }
}
