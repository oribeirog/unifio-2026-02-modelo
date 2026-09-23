package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Categoria;
import br.edu.unifio.eventos.entidades.Evento;
import br.edu.unifio.eventos.entidades.Local;
import br.edu.unifio.eventos.entidades.Palestrante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EventoRepositoryTest {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private PalestranteRepository palestranteRepository;

    private Categoria criarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria de Teste");
        categoria.setDescricao("Categoria criada para teste");
        return categoriaRepository.save(categoria);
    }

    private Local criarLocal() {
        Local local = new Local();
        local.setNome("Local de Teste");
        local.setEndereco("Rua de Teste, 100");
        local.setCapacidade(300);
        return localRepository.save(local);
    }

    private Palestrante criarPalestrante() {
        Palestrante palestrante = new Palestrante();
        palestrante.setNome("Palestrante de Teste");
        palestrante.setMiniBio("Mini bio do palestrante");
        palestrante.setEmail("palestrante@teste.com");
        return palestranteRepository.save(palestrante);
    }

    private Evento criarEvento() {
        Categoria categoria = criarCategoria();
        Local local = criarLocal();
        Palestrante palestrante = criarPalestrante();

        Evento evento = new Evento();
        evento.setNome("Evento de Teste");
        evento.setDescricao("Descrição do evento de teste");
        evento.setDataInicio(LocalDateTime.of(2026, 11, 10, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 11, 10, 22, 0));
        evento.setCapacidade(150);
        evento.setStatus("ATIVO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        return repository.save(evento);
    }

    @Test
    void deveInserirEvento() {
        Evento salvo = criarEvento();

        assertNotNull(salvo.getId());
        assertEquals("Evento de Teste", salvo.getNome());
        assertEquals(150, salvo.getCapacidade());
        assertNotNull(salvo.getCategoria().getId());
        assertNotNull(salvo.getLocal().getId());
        assertNotNull(salvo.getPalestrante().getId());
    }

    @Test
    void deveBuscarEventoPorId() {
        Evento salvo = criarEvento();

        Evento encontrado =
                repository.findById(salvo.getId()).orElseThrow();

        assertEquals(salvo.getId(), encontrado.getId());
        assertEquals("Evento de Teste", encontrado.getNome());
        assertEquals(150, encontrado.getCapacidade());
    }

    @Test
    void deveListarEventos() {
        Evento evento1 = criarEvento();
        Evento evento2 = criarEvento();

        List<Evento> eventos = repository.findAll();

        assertTrue(eventos.size() >= 2);
        assertTrue(eventos.stream()
                .anyMatch(e -> e.getId().equals(evento1.getId())));
        assertTrue(eventos.stream()
                .anyMatch(e -> e.getId().equals(evento2.getId())));
    }

    @Test
    void deveAlterarEvento() {
        Evento salvo = criarEvento();

        Long idOriginal = salvo.getId();

        salvo.setNome("Nome Alterado");
        salvo.setCapacidade(250);

        repository.save(salvo);

        Evento encontrado =
                repository.findById(idOriginal).orElseThrow();

        assertEquals(idOriginal, encontrado.getId());
        assertEquals("Nome Alterado", encontrado.getNome());
        assertEquals(250, encontrado.getCapacidade());
    }

    @Test
    void deveExcluirEvento() {
        Evento salvo = criarEvento();

        assertTrue(repository.existsById(salvo.getId()));

        repository.deleteById(salvo.getId());

        assertFalse(repository.existsById(salvo.getId()));
    }
}