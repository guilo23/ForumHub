package com.bia.ForumHub.service;

import com.bia.ForumHub.dto.RequestPerfil;
import com.bia.ForumHub.model.Perfil;
import com.bia.ForumHub.repository.PerfilRepository;
import com.bia.ForumHub.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Perfil createPerfil(Long userId, RequestPerfil dados){
         var usuario = usuarioRepositorio.findById(userId).orElseThrow(NullPointerException::new);
         var perfil = new Perfil(
                 null,
                 dados.nomePerfil(),
                 usuario
         );
         perfilRepository.save(perfil);
         return perfil;
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
