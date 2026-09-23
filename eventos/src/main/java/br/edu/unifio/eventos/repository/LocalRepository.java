package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Long> {
}