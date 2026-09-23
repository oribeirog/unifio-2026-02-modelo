package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Participante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ParticipanteRepositoryTest {

    @Autowired
    private ParticipanteRepository repository;

    @Test
    void deveInserirParticipante() {
        Participante participante = new Participante();
        participante.setNome("Otavio Teste");
        participante.setEmail("otavio@teste.com");
        participante.setTelefone("14999999999");

        Participante salvo = repository.save(participante);

        assertNotNull(salvo.getId());
        assertEquals("Otavio Teste", salvo.getNome());
        assertEquals("otavio@teste.com", salvo.getEmail());
    }

    @Test
    void deveBuscarParticipantePorId() {
        Participante participante = new Participante();
        participante.setNome("Carlos Teste");
        participante.setEmail("carlos@teste.com");
        participante.setTelefone("14988888888");

        Participante salvo = repository.save(participante);

        Participante encontrado =
                repository.findById(salvo.getId()).orElseThrow();

        assertEquals(salvo.getId(), encontrado.getId());
        assertEquals("Carlos Teste", encontrado.getNome());
        assertEquals("carlos@teste.com", encontrado.getEmail());
    }

    @Test
    void deveListarParticipantes() {
        Participante participante1 = new Participante();
        participante1.setNome("Participante A");
        participante1.setEmail("a@teste.com");
        participante1.setTelefone("14990000001");

        Participante participante2 = new Participante();
        participante2.setNome("Participante B");
        participante2.setEmail("b@teste.com");
        participante2.setTelefone("14990000002");

        Participante salvo1 = repository.save(participante1);
        Participante salvo2 = repository.save(participante2);

        List<Participante> participantes = repository.findAll();

        assertTrue(participantes.size() >= 2);
        assertTrue(participantes.stream().anyMatch(p -> p.getId().equals(salvo1.getId())));
        assertTrue(participantes.stream().anyMatch(p -> p.getId().equals(salvo2.getId())));
    }

    @Test
    void deveAlterarParticipante() {
        Participante participante = new Participante();
        participante.setNome("Nome Original");
        participante.setEmail("original@teste.com");
        participante.setTelefone("14991111111");

        Participante salvo = repository.save(participante);

        salvo.setNome("Nome Alterado");
        salvo.setTelefone("14992222222");
        repository.save(salvo);

        Participante encontrado =
                repository.findById(salvo.getId()).orElseThrow();

        assertEquals("Nome Alterado", encontrado.getNome());
        assertEquals("14992222222", encontrado.getTelefone());
    }

    @Test
    void deveExcluirParticipante() {
        Participante participante = new Participante();
        participante.setNome("Participante para Excluir");
        participante.setEmail("excluir@teste.com");
        participante.setTelefone("14993333333");

        Participante salvo = repository.save(participante);

        assertTrue(repository.existsById(salvo.getId()));

        repository.deleteById(salvo.getId());

        assertFalse(repository.existsById(salvo.getId()));
    }
}