package com.bia.ForumHub.dto;

import com.bia.ForumHub.model.Resposta;
import com.bia.ForumHub.model.Topico;

public record RespostaResponse(String topicoTitulo,
                               String mensagem) {
    public RespostaResponse(Resposta resposta) {
        this(
               resposta.getTopico().getTitulo(),
               resposta.getMensagem()
        );
    }
}
