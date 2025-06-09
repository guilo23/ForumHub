package com.bia.ForumHub.controller;

import com.bia.ForumHub.dto.RequestResposta;
import com.bia.ForumHub.dto.RequestUsuario;
import com.bia.ForumHub.dto.RequestPerfil;
import com.bia.ForumHub.dto.UpdateResposta;
import com.bia.ForumHub.service.PerfilService;
import com.bia.ForumHub.service.RespostaService;
import com.bia.ForumHub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PerfilService perfilService;
    @Autowired
    private RespostaService respostaService;

    @PostMapping("/{userId}")
    public ResponseEntity addPerfil(@PathVariable Long userId,@RequestBody RequestPerfil nomePerfil){
        var perfil = perfilService.createPerfil(userId,nomePerfil);
        return ResponseEntity.ok().body(perfil);
    }
    @PutMapping("/{id}")
    public ResponseEntity updatePerfil(@RequestBody RequestPerfil dados,
                                         @PathVariable Long id){
        perfilService.PerfilUpdate(dados,id);
        return ResponseEntity.ok().body("Perfil Altualizada com sucesso");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeletePerfil(@PathVariable Long id){
        perfilService.PerfilDelete(id);
        return ResponseEntity.ok().body("Perfil deletada com sucesso");
    }
}
