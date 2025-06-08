package com.bia.ForumHub.controller;

import com.bia.ForumHub.dto.RequestResposta;
import com.bia.ForumHub.dto.UpdateResposta;
import com.bia.ForumHub.service.PerfilService;
import com.bia.ForumHub.service.RespostaService;
import com.bia.ForumHub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PerfilService perfilService;
    @Autowired
    private RespostaService respostaService;


    @PostMapping("{perfilId}/resposta")
    public ResponseEntity AddResposta(@RequestBody RequestResposta dados,
                                      @PathVariable Long perfilId){
        respostaService.createResposta(dados,perfilId);
        return ResponseEntity.ok().body("Resposta adcionada com sucesso");
    }
    @GetMapping("/listar")
    public ResponseEntity allRespostas(){
        var respostas = respostaService.Allrespostas();
        return ResponseEntity.ok().body(respostas);
    }
    @GetMapping("/{Id}")
    public ResponseEntity RespostaById(@PathVariable Long id){
        var respostas = respostaService.respostaById(id);
        return ResponseEntity.ok().body(respostas);
    }
    @GetMapping("/{perfilId}")
    public ResponseEntity RespostasByPerfilId(@PathVariable Long id){
        var respostas = respostaService.respostasByPerfil(id);
        return ResponseEntity.ok().body(respostas);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateResposta(@RequestBody UpdateResposta dados,
                                         @PathVariable Long id){
        respostaService.RespostaUpdate(dados,id);
        return ResponseEntity.ok().body("Resposta Altualizada com sucesso");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteResposta(@PathVariable Long id){
        respostaService.RespostaDelete(id);
        return ResponseEntity.ok().body("Resposta deletada com sucesso");
    }


}
