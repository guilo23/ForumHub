package com.bia.ForumHub.service;

import com.bia.ForumHub.dto.RequestResposta;
import com.bia.ForumHub.dto.RespostaResponse;
import com.bia.ForumHub.dto.UpdateResposta;
import com.bia.ForumHub.model.Resposta;
import com.bia.ForumHub.model.Usuario;
import com.bia.ForumHub.repository.PerfilRepository;
import com.bia.ForumHub.repository.RespostaRepository;
import com.bia.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;

@Service
public class RespostaService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private RespostaRepository respostaRepository;


    public void createResposta(RequestResposta dados, Long perfilId, Usuario usuarioLogado){
        var perfil = perfilRepository.findById(perfilId).orElseThrow(NullPointerException::new);
        var topicoAtual = topicoRepository.findTopicoByTitulo(dados.topicoTitulo());
        if (!perfil.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode usar este perfil.");
        }
        var resposta = new Resposta(
                null,
                dados.mensagem(),
                topicoAtual,
                LocalDate.now(),
                perfil
        );
        respostaRepository.save(resposta);
        topicoAtual.getRespostas().add(resposta);
    }
    public RespostaResponse respostaById(Long id){
        var resposta = respostaRepository.findById(id).orElseThrow(NullPointerException::new);
        return new RespostaResponse(resposta);
    }
    public List<RespostaResponse> Allrespostas(){
        var respostas = respostaRepository.findAll();
        return  respostas.stream()
                .map(RespostaResponse::new)
                .collect(Collectors.toList());
    }
    public List<RespostaResponse> respostasByPerfil(Long id, Usuario usuarioLogado){
        var respostas = respostaRepository.findByPerfilId(id);
        var autor = perfilRepository.findById(id).orElseThrow(NullPointerException::new);
        if (!autor.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode usar este perfil.");
        }
        return  respostas.stream()
                .map(RespostaResponse::new)
                .collect(Collectors.toList());
    }
    public void RespostaUpdate(UpdateResposta dados, Long id, Long perfilId, Usuario usuarioLogado){
        var autor = perfilRepository.findById(perfilId).orElseThrow(NullPointerException::new);
        if (!autor.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode usar este perfil.");
        }
        var resposta = respostaRepository.findById(id).orElseThrow();
        resposta.setMensagem(dados.mensagem());
        respostaRepository.save(resposta);
    }
    public void RespostaDelete(Long id, Long perfilId, Usuario usuarioLogado){
        var autor = perfilRepository.findById(perfilId).orElseThrow(NullPointerException::new);
        if (!autor.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode usar este perfil.");
        }

        respostaRepository.deleteById(id);
    }

}
