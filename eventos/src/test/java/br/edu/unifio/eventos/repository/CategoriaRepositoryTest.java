package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @Test
    void deveInserirCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Esportes");
        categoria.setDescricao("Eventos relacionados a esportes");

        Categoria salva = repository.save(categoria);

        assertNotNull(salva.getId());
        assertEquals("Esportes", salva.getNome());
        assertEquals("Eventos relacionados a esportes", salva.getDescricao());
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria();
        categoria.setNome("Tecnologia Teste");
        categoria.setDescricao("Categoria para teste");

        Categoria salva = repository.save(categoria);

        Categoria encontrada = repository.findById(salva.getId()).orElseThrow();

        assertEquals(salva.getId(), encontrada.getId());
        assertEquals("Tecnologia Teste", encontrada.getNome());
        assertEquals("Categoria para teste", encontrada.getDescricao());
    }

    @Test
    void deveListarCategorias() {
        Categoria categoria1 = new Categoria();
        categoria1.setNome("Categoria A");
        categoria1.setDescricao("Descrição A");

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Categoria B");
        categoria2.setDescricao("Descrição B");

        Categoria salva1 = repository.save(categoria1);
        Categoria salva2 = repository.save(categoria2);

        List<Categoria> categorias = repository.findAll();

        assertTrue(categorias.size() >= 2);
        assertTrue(categorias.stream().anyMatch(c -> c.getId().equals(salva1.getId())));
        assertTrue(categorias.stream().anyMatch(c -> c.getId().equals(salva2.getId())));
    }

    @Test
    void deveAlterarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Nome Original");
        categoria.setDescricao("Descrição Original");

        Categoria salva = repository.save(categoria);

        salva.setNome("Nome Alterado");
        salva.setDescricao("Descrição Alterada");
        repository.save(salva);

        Categoria encontrada = repository.findById(salva.getId()).orElseThrow();

        assertEquals("Nome Alterado", encontrada.getNome());
        assertEquals("Descrição Alterada", encontrada.getDescricao());
    }

    @Test
    void deveExcluirCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria para Excluir");
        categoria.setDescricao("Será excluída");

        Categoria salva = repository.save(categoria);

        assertTrue(repository.existsById(salva.getId()));

        repository.deleteById(salva.getId());

        assertFalse(repository.existsById(salva.getId()));
    }
}