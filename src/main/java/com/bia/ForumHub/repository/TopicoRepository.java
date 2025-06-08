package com.bia.ForumHub.repository;

import com.bia.ForumHub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {
    List<Topico> findByPerfilId(Long id);

    Topico findTopicoByTitulo(String titulo);
}
