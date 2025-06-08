package com.bia.ForumHub.dto;

import com.bia.ForumHub.model.Resposta;
import com.bia.ForumHub.model.Topico;

import java.util.List;

public record TopicoResponse(String mensagem,
                             String titulo,
                             String nomePerfil,
                             String nomeCurso,
                             List<Resposta> respostas
                        ) {
    public TopicoResponse(Topico topico) {
        this(
                topico.getMensagem(),
                topico.getTitulo(),
                topico.getPerfil().getNomeDoPerfil(),
                topico.getCurso().getNome(),
                topico.getRespostas()
        );
    }
}
