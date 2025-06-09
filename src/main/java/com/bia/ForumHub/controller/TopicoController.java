package com.bia.ForumHub.controller;

import com.bia.ForumHub.dto.RequestTopico;
import com.bia.ForumHub.dto.UpdateResposta;
import com.bia.ForumHub.dto.UpdateTopico;
import com.bia.ForumHub.model.Usuario;
import com.bia.ForumHub.repository.PerfilRepository;
import com.bia.ForumHub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;
    @Autowired
    private PerfilRepository perfilRepository;


    @GetMapping("lista/{id}")
    public ResponseEntity topicsById(@PathVariable Long id){
        var topico = topicoService.topicosById(id);
        return ResponseEntity.ok().body(topico);
    }
    @GetMapping("/{id}")
    public ResponseEntity topicsByPerfilId(@PathVariable Long id,Authentication authentication){
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        var topicos = topicoService.topicosByPerfilId(id,usuarioLogado);
        return ResponseEntity.ok().body(topicos);
    }

    @PostMapping("/{perfilId}")
    public ResponseEntity criarTopico(@RequestBody RequestTopico dados,
                                      @PathVariable Long perfilId,
                                      Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        topicoService.addTopicos(dados, perfilId, usuarioLogado);

        return ResponseEntity.ok().build();
    }
    @PutMapping("{perfilId}/{id}")
    public ResponseEntity updateTopico(@RequestBody UpdateTopico dados,
                                         @PathVariable Long perfilId,
                                         @PathVariable Long id,
                                       Authentication authentication){
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        topicoService.topicoUpdate(dados,perfilId,id,usuarioLogado);
        return ResponseEntity.ok().body("topico Altualizado com sucesso");
    }
    @DeleteMapping("{perfilId}/{id}")
    public ResponseEntity DeleteTopico(@PathVariable Long id,
                                       @PathVariable Long perfilId,
                                       Authentication authentication){
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        topicoService.topicoDelete(id,perfilId,usuarioLogado);
        return ResponseEntity.ok().body("topico deletado com sucesso");
    }

}
