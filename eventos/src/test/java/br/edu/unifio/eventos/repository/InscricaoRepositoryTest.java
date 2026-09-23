package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Categoria;
import br.edu.unifio.eventos.entidades.Evento;
import br.edu.unifio.eventos.entidades.Inscricao;
import br.edu.unifio.eventos.entidades.Local;
import br.edu.unifio.eventos.entidades.Palestrante;
import br.edu.unifio.eventos.entidades.Participante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InscricaoRepositoryTest {

    @Autowired
    private InscricaoRepository repository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private PalestranteRepository palestranteRepository;

    private Participante criarParticipante(String nome, String email) {
        Participante participante = new Participante();
        participante.setNome(nome);
        participante.setEmail(email);
        participante.setTelefone("(43) 99999-9999");

        return participanteRepository.save(participante);
    }

    private Evento criarEvento() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria de Teste");
        categoria.setDescricao("Categoria para teste");
        categoria = categoriaRepository.save(categoria);

        Local local = new Local();
        local.setNome("Local de Teste");
        local.setEndereco("Rua de Teste, 100");
        local.setCapacidade(300);
        local = localRepository.save(local);

        Palestrante palestrante = new Palestrante();
        palestrante.setNome("Palestrante de Teste");
        palestrante.setMiniBio("Mini bio de teste");
        palestrante.setEmail("palestrante@teste.com");
        palestrante = palestranteRepository.save(palestrante);

        Evento evento = new Evento();
        evento.setNome("Evento de Teste");
        evento.setDescricao("Descrição do evento");
        evento.setDataInicio(LocalDateTime.of(2026, 11, 10, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 11, 10, 22, 0));
        evento.setCapacidade(150);
        evento.setStatus("ATIVO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        return eventoRepository.save(evento);
    }

    @Test
    void deveInserirInscricao() {
        Evento evento = criarEvento();
        Participante participante =
                criarParticipante("Participante Teste", "participante@teste.com");

        Inscricao inscricao = new Inscricao();
        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("CONFIRMADA");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        Inscricao salva = repository.save(inscricao);

        assertNotNull(salva.getId());
        assertEquals("CONFIRMADA", salva.getStatus());
        assertEquals(evento.getId(), salva.getEvento().getId());
        assertEquals(participante.getId(), salva.getParticipante().getId());
    }

    @Test
    void deveBuscarInscricaoPorId() {
        Evento evento = criarEvento();
        Participante participante =
                criarParticipante("Participante Busca", "busca@teste.com");

        Inscricao inscricao = new Inscricao();
        inscricao.setDataInscricao(
                LocalDateTime.of(2026, 11, 1, 10, 0));
        inscricao.setStatus("PENDENTE");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        Inscricao salva = repository.save(inscricao);

        Inscricao encontrada =
                repository.findById(salva.getId()).orElseThrow();

        assertEquals(salva.getId(), encontrada.getId());
        assertEquals("PENDENTE", encontrada.getStatus());
        assertEquals(evento.getId(), encontrada.getEvento().getId());
        assertEquals(participante.getId(),
                encontrada.getParticipante().getId());
    }

    @Test
    void deveListarInscricoes() {
        Evento evento = criarEvento();

        Participante participante1 =
                criarParticipante("Participante 1", "p1@teste.com");

        Participante participante2 =
                criarParticipante("Participante 2", "p2@teste.com");

        Inscricao inscricao1 = new Inscricao();
        inscricao1.setDataInscricao(
                LocalDateTime.of(2026, 11, 2, 10, 0));
        inscricao1.setStatus("CONFIRMADA");
        inscricao1.setEvento(evento);
        inscricao1.setParticipante(participante1);

        Inscricao inscricao2 = new Inscricao();
        inscricao2.setDataInscricao(
                LocalDateTime.of(2026, 11, 3, 10, 0));
        inscricao2.setStatus("PENDENTE");
        inscricao2.setEvento(evento);
        inscricao2.setParticipante(participante2);

        Inscricao salva1 = repository.save(inscricao1);
        Inscricao salva2 = repository.save(inscricao2);

        List<Inscricao> inscricoes = repository.findAll();

        assertTrue(inscricoes.size() >= 2);
        assertTrue(inscricoes.stream()
                .anyMatch(i -> i.getId().equals(salva1.getId())));
        assertTrue(inscricoes.stream()
                .anyMatch(i -> i.getId().equals(salva2.getId())));
    }

    @Test
    void deveAlterarInscricao() {
        Evento evento = criarEvento();
        Participante participante =
                criarParticipante("Participante Alteracao",
                        "alteracao@teste.com");

        Inscricao inscricao = new Inscricao();
        inscricao.setDataInscricao(
                LocalDateTime.of(2026, 11, 5, 10, 0));
        inscricao.setStatus("PENDENTE");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        Inscricao salva = repository.save(inscricao);

        Long idOriginal = salva.getId();

        salva.setStatus("CONFIRMADA");
        repository.save(salva);

        Inscricao encontrada =
                repository.findById(idOriginal).orElseThrow();

        assertEquals(idOriginal, encontrada.getId());
        assertEquals("CONFIRMADA", encontrada.getStatus());
    }

    @Test
    void deveExcluirInscricao() {
        Evento evento = criarEvento();
        Participante participante =
                criarParticipante("Participante Exclusao",
                        "exclusao@teste.com");

        Inscricao inscricao = new Inscricao();
        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("PENDENTE");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        Inscricao salva = repository.save(inscricao);

        assertTrue(repository.existsById(salva.getId()));

        repository.deleteById(salva.getId());

        assertFalse(repository.existsById(salva.getId()));
    }
}