package com.bia.ForumHub.repository;

import com.bia.ForumHub.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta,Long> {
    List<Resposta> findByPerfilId(Long id);
}
