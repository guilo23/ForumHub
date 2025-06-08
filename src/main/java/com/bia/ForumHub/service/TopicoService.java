package com.bia.ForumHub.service;

import com.bia.ForumHub.dto.RequestPerfil;
import com.bia.ForumHub.dto.RequestTopico;
import com.bia.ForumHub.dto.TopicoResponse;
import com.bia.ForumHub.dto.UpdateTopico;
import com.bia.ForumHub.model.Curso;
import com.bia.ForumHub.model.Topico;
import com.bia.ForumHub.repository.PerfilRepository;
import com.bia.ForumHub.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    public void addTopicos(RequestTopico dados, Long perfilId) {
        var autor= perfilRepository.findById(perfilId).orElseThrow(NullPointerException::new);
        var topico = new Topico(
                null,
                dados.titulo(),
                dados.mensagem(),
                LocalDate.now(),
                true,
                autor,
                new Curso(dados.nomeCurso(),dados.categoriaCurso()),
                List.of()
        );
        topicoRepository.save(topico);
    }
    public List<TopicoResponse> allTopics(){
        var topicos = topicoRepository.findAll();
        return  topicos.stream()
                .map(TopicoResponse::new)
                .collect(Collectors.toList());
    }
    public List<TopicoResponse> topicosByPerfilId(Long id){
        var topicos = topicoRepository.findByPerfilId(id);

        return topicos.stream()
                .map(TopicoResponse::new)
                .collect(Collectors.toList());
    }
    public TopicoResponse topicosById(Long id){
        var dados = topicoRepository.findById(id).orElseThrow();
        return new TopicoResponse(dados);
    }
    public void topicoUpdate(UpdateTopico dados, Long id){
        var topico = topicoRepository.findById(id).orElseThrow();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topicoRepository.save(topico);
    }
    public void topicoDelete(Long id){
        topicoRepository.deleteById(id);
    }
}
