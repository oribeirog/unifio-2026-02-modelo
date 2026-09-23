package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {
}