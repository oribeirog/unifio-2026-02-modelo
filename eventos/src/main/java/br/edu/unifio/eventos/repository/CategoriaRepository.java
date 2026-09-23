package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}