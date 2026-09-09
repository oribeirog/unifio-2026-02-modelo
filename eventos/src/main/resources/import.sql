-- Categorias

INSERT INTO categoria (id, nome, descricao) VALUES (1, 'Tecnologia', 'Eventos relacionados a tecnologia e inovação');

INSERT INTO categoria (id, nome, descricao) VALUES (2, 'Negócios', 'Eventos sobre empreendedorismo e negócios');

INSERT INTO categoria (id, nome, descricao) VALUES (3, 'Educação', 'Eventos relacionados a ensino e aprendizagem');

INSERT INTO categoria (id, nome, descricao) VALUES (4, 'Programação', 'Eventos sobre desenvolvimento de software');

INSERT INTO categoria (id, nome, descricao) VALUES (5, 'Marketing', 'Eventos sobre marketing e comunicação');


-- Locais

INSERT INTO local (id, nome, endereco, capacidade) VALUES (1, 'Auditório UNIFIO', 'Av. Dr. Armando Sales de Oliveira, 1000', 300);

INSERT INTO local (id, nome, endereco, capacidade) VALUES (2, 'Centro de Convenções', 'Rua das Flores, 500', 500);

INSERT INTO local (id, nome, endereco, capacidade) VALUES (3, 'Sala de Tecnologia', 'Av. Principal, 200', 100);

INSERT INTO local (id, nome, endereco, capacidade) VALUES (4, 'Espaço Empresarial', 'Rua dos Empresários, 150', 200);

INSERT INTO local (id, nome, endereco, capacidade) VALUES (5, 'Laboratório de Informática', 'Av. Universitária, 300', 80);


-- Palestrantes

INSERT INTO palestrante (id, nome, mini_bio, email) VALUES (1, 'Carlos Silva', 'Especialista em desenvolvimento de software.', 'carlos@email.com');

INSERT INTO palestrante (id, nome, mini_bio, email) VALUES (2, 'Ana Souza', 'Consultora de negócios e empreendedorismo.', 'ana@email.com');

INSERT INTO palestrante (id, nome, mini_bio, email) VALUES (3, 'Marcos Oliveira', 'Professor e pesquisador na área de tecnologia.', 'marcos@email.com');

INSERT INTO palestrante (id, nome, mini_bio, email) VALUES (4, 'Juliana Santos', 'Desenvolvedora de software e especialista em Java.', 'juliana@email.com');

INSERT INTO palestrante (id, nome, mini_bio, email) VALUES (5, 'Rafael Costa', 'Especialista em marketing digital.', 'rafael@email.com');


-- Participantes

INSERT INTO participante (id, nome, email, telefone) VALUES (1, 'João Pereira', 'joao@email.com', '14999990001');

INSERT INTO participante (id, nome, email, telefone) VALUES (2, 'Maria Oliveira', 'maria@email.com', '14999990002');

INSERT INTO participante (id, nome, email, telefone) VALUES (3, 'Pedro Santos', 'pedro@email.com', '14999990003');

INSERT INTO participante (id, nome, email, telefone) VALUES (4, 'Lucas Almeida', 'lucas@email.com', '14999990004');

INSERT INTO participante (id, nome, email, telefone) VALUES (5, 'Beatriz Costa', 'beatriz@email.com', '14999990005');


-- Eventos

INSERT INTO evento (id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES (1, 'Semana de Tecnologia', 'Evento sobre tecnologia e inovação.', '2026-09-15 19:00:00', '2026-09-15 22:00:00', 300, 'ATIVO', 1, 1, 1);

INSERT INTO evento (id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES (2, 'Empreendedorismo na Prática', 'Palestra sobre empreendedorismo e negócios.', '2026-09-20 19:00:00', '2026-09-20 21:00:00', 200, 'ATIVO', 2, 4, 2);

INSERT INTO evento (id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES (3, 'Educação e Tecnologia', 'Discussão sobre o uso da tecnologia na educação.', '2026-09-25 18:30:00', '2026-09-25 21:30:00', 100, 'ATIVO', 3, 3, 3);

INSERT INTO evento (id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES (4, 'Workshop Java e Spring', 'Workshop prático de desenvolvimento utilizando Java e Spring.', '2026-10-05 19:00:00', '2026-10-05 22:00:00', 80, 'ATIVO', 4, 5, 4);

INSERT INTO evento (id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id) VALUES (5, 'Marketing Digital', 'Palestra sobre estratégias de marketing digital.', '2026-10-10 19:00:00', '2026-10-10 21:00:00', 500, 'ATIVO', 5, 2, 5);


-- Inscricoes

INSERT INTO inscricao (id, data_inscricao, status, evento_id, participante_id) VALUES(1, '2026-09-01 10:00:00', 'CONFIRMADA', 1, 1);

INSERT INTO inscricao (id, data_inscricao, status, evento_id, participante_id) VALUES (2, '2026-09-02 11:00:00', 'CONFIRMADA', 1, 2);

INSERT INTO inscricao (id, data_inscricao, status, evento_id, participante_id) VALUES (3, '2026-09-03 14:00:00', 'CONFIRMADA', 2, 3);

INSERT INTO inscricao (id, data_inscricao, status, evento_id, participante_id) VALUES (4, '2026-09-04 15:00:00', 'PENDENTE', 3, 4);

INSERT INTO inscricao (id, data_inscricao, status, evento_id, participante_id) VALUES (5, '2026-09-05 16:00:00', 'CONFIRMADA', 4, 5);