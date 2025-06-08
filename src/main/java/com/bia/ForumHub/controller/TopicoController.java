package com.bia.ForumHub.controller;

import com.bia.ForumHub.dto.RequestTopico;
import com.bia.ForumHub.dto.UpdateResposta;
import com.bia.ForumHub.dto.UpdateTopico;
import com.bia.ForumHub.repository.PerfilRepository;
import com.bia.ForumHub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping("/lista")
    public ResponseEntity alltopics(){
        var topicos = topicoService.allTopics();
        return ResponseEntity.ok().body(topicos);
    }
    @GetMapping("lista/{id}")
    public ResponseEntity topicsById(@PathVariable Long id){
        var topico = topicoService.topicosById(id);
        return ResponseEntity.ok().body(topico);
    }
    @GetMapping("/{id}")
    public ResponseEntity topicsByPerfilId(@PathVariable Long id){
        var topicos = topicoService.topicosByPerfilId(id);
        return ResponseEntity.ok().body(topicos);
    }

    @PostMapping("/{perfilId}")
    public ResponseEntity addTopico(@RequestBody RequestTopico dados,
                                    @PathVariable Long perfilId){
        topicoService.addTopicos(dados,perfilId);
        return ResponseEntity.ok().body("Deu certo Só alegria");
    }
    @PutMapping("/{id}")
    public ResponseEntity updateTopico(@RequestBody UpdateTopico dados,
                                         @PathVariable Long id){
        topicoService.topicoUpdate(dados,id);
        return ResponseEntity.ok().body("topico Altualizado com sucesso");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteTopico(@PathVariable Long id){
        topicoService.topicoDelete(id);
        return ResponseEntity.ok().body("topico deletado com sucesso");
    }

}
