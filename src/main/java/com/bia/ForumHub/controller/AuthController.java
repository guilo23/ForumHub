package com.bia.ForumHub.controller;

import com.bia.ForumHub.dto.RequestAuth;
import com.bia.ForumHub.dto.RequestUsuario;
import com.bia.ForumHub.model.Usuario;
import com.bia.ForumHub.service.TokenService;
import com.bia.ForumHub.service.TopicoService;
import com.bia.ForumHub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TopicoService topicoService;

    @PostMapping("/register")
    private ResponseEntity addUser(@RequestBody RequestUsuario dados){
        usuarioService.createUser(dados);
        return  ResponseEntity.ok().body(dados);
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody RequestAuth dados){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(dados.senha()));
        String senhaCriptografada = encoder.encode(dados.senha());
        System.out.println(senhaCriptografada);


        var Authtoken = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        var auth  = manager.authenticate(Authtoken);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return  ResponseEntity.ok(token);
    }
    @GetMapping("/lista")
    public ResponseEntity alltopics(){
        var topicos = topicoService.allTopics();
        return ResponseEntity.ok().body(topicos);
    }
}
