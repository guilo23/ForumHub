package com.bia.ForumHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String mensagem;

    @ManyToOne@JoinColumn(name="topico_id")
    @JsonIgnore
    private Topico topico;
    private LocalDate dataCriacao;
    @ManyToOne@JoinColumn(name="perfil_id")
    private Perfil perfil;

}
