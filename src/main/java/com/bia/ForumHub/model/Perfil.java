package com.bia.ForumHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nomeDoPerfil;
    private String authority;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @Override
    public String getAuthority() {
        return authority;
    }
}
