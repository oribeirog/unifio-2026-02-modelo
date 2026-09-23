package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}