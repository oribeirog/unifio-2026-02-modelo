package br.edu.unifio.eventos.repository;

import br.edu.unifio.eventos.entidades.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LocalRepositoryTest {

    @Autowired
    private LocalRepository repository;

    @Test
    void deveInserirLocal() {
        Local local = new Local();
        local.setNome("Sala de Testes");
        local.setEndereco("Rua dos Testes, 100");
        local.setCapacidade(100);

        Local salvo = repository.save(local);

        assertNotNull(salvo.getId());
        assertEquals("Sala de Testes", salvo.getNome());
        assertEquals(100, salvo.getCapacidade());
    }

    @Test
    void deveBuscarLocalPorId() {
        Local local = new Local();
        local.setNome("Auditório Teste");
        local.setEndereco("Av. Teste, 200");
        local.setCapacidade(250);

        Local salvo = repository.save(local);

        Local encontrado = repository.findById(salvo.getId()).orElseThrow();

        assertEquals(salvo.getId(), encontrado.getId());
        assertEquals("Auditório Teste", encontrado.getNome());
        assertEquals(250, encontrado.getCapacidade());
    }

    @Test
    void deveListarLocais() {
        Local local1 = new Local();
        local1.setNome("Local A");
        local1.setEndereco("Endereço A");
        local1.setCapacidade(50);

        Local local2 = new Local();
        local2.setNome("Local B");
        local2.setEndereco("Endereço B");
        local2.setCapacidade(100);

        Local salvo1 = repository.save(local1);
        Local salvo2 = repository.save(local2);

        List<Local> locais = repository.findAll();

        assertTrue(locais.size() >= 2);
        assertTrue(locais.stream().anyMatch(l -> l.getId().equals(salvo1.getId())));
        assertTrue(locais.stream().anyMatch(l -> l.getId().equals(salvo2.getId())));
    }

    @Test
    void deveAlterarLocal() {
        Local local = new Local();
        local.setNome("Nome Original");
        local.setEndereco("Endereço Original");
        local.setCapacidade(100);

        Local salvo = repository.save(local);

        salvo.setNome("Nome Alterado");
        salvo.setCapacidade(200);
        repository.save(salvo);

        Local encontrado = repository.findById(salvo.getId()).orElseThrow();

        assertEquals("Nome Alterado", encontrado.getNome());
        assertEquals(200, encontrado.getCapacidade());
    }

    @Test
    void deveExcluirLocal() {
        Local local = new Local();
        local.setNome("Local para Excluir");
        local.setEndereco("Rua para Excluir");
        local.setCapacidade(50);

        Local salvo = repository.save(local);

        assertTrue(repository.existsById(salvo.getId()));

        repository.deleteById(salvo.getId());

        assertFalse(repository.existsById(salvo.getId()));
    }
}