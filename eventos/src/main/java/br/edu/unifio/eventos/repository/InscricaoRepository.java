package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
}