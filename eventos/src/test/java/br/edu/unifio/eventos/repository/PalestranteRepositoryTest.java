package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Palestrante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PalestranteRepositoryTest {

    @Autowired
    private PalestranteRepository repository;

    @Test
    void deveInserirPalestrante() {
        Palestrante palestrante = new Palestrante();
        palestrante.setNome("José Teste");
        palestrante.setMiniBio("Especialista em tecnologia.");
        palestrante.setEmail("jose@teste.com");

        Palestrante salvo = repository.save(palestrante);

        assertNotNull(salvo.getId());
        assertEquals("José Teste", salvo.getNome());
        assertEquals("jose@teste.com", salvo.getEmail());
    }

    @Test
    void deveBuscarPalestrantePorId() {
        Palestrante palestrante = new Palestrante();
        palestrante.setNome("Maria Teste");
        palestrante.setMiniBio("Profissional de desenvolvimento.");
        palestrante.setEmail("maria@teste.com");

        Palestrante salvo = repository.save(palestrante);

        Palestrante encontrado =
                repository.findById(salvo.getId()).orElseThrow();

        assertEquals(salvo.getId(), encontrado.getId());
        assertEquals("Maria Teste", encontrado.getNome());
        assertEquals("maria@teste.com", encontrado.getEmail());
    }

    @Test
    void deveListarPalestrantes() {
        Palestrante palestrante1 = new Palestrante();
        palestrante1.setNome("Palestrante A");
        palestrante1.setMiniBio("Bio A");
        palestrante1.setEmail("a@teste.com");

        Palestrante palestrante2 = new Palestrante();
        palestrante2.setNome("Palestrante B");
        palestrante2.setMiniBio("Bio B");
        palestrante2.setEmail("b@teste.com");

        Palestrante salvo1 = repository.save(palestrante1);
        Palestrante salvo2 = repository.save(palestrante2);

        List<Palestrante> palestrantes = repository.findAll();

        assertTrue(palestrantes.size() >= 2);
        assertTrue(palestrantes.stream().anyMatch(p -> p.getId().equals(salvo1.getId())));
        assertTrue(palestrantes.stream().anyMatch(p -> p.getId().equals(salvo2.getId())));
    }

    @Test
    void deveAlterarPalestrante() {
        Palestrante palestrante = new Palestrante();
        palestrante.setNome("Nome Original");
        palestrante.setMiniBio("Bio Original");
        palestrante.setEmail("original@teste.com");

        Palestrante salvo = repository.save(palestrante);

        salvo.setNome("Nome Alterado");
        salvo.setEmail("alterado@teste.com");
        repository.save(salvo);

        Palestrante encontrado =
                repository.findById(salvo.getId()).orElseThrow();

        assertEquals("Nome Alterado", encontrado.getNome());
        assertEquals("alterado@teste.com", encontrado.getEmail());
    }

    @Test
    void deveExcluirPalestrante() {
        Palestrante palestrante = new Palestrante();
        palestrante.setNome("Palestrante para Excluir");
        palestrante.setMiniBio("Bio");
        palestrante.setEmail("excluir@teste.com");

        Palestrante salvo = repository.save(palestrante);

        assertTrue(repository.existsById(salvo.getId()));

        repository.deleteById(salvo.getId());

        assertFalse(repository.existsById(salvo.getId()));
    }
}