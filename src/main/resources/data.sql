INSERT INTO helpdesk.pessoa (dtype, id, cpf, data_criacao, email, nome, senha) VALUES ('TECNICO', 1, '33985840040', '2024-10-30', 'moises@ig.com', 'Moises Moura', '$2a$10$1VJWx.nnwjEWprh3X9WyAu0EjixPkSNyyF0EjNABAXZwa5M4eGdJW');

INSERT INTO helpdesk.pessoa (dtype, id, cpf, data_criacao, email, nome, senha) VALUES ('CLIENTE', 2, '59510644064', '2024-10-30', 'veronica@ig.com', 'Veronica', '$2a$10$4akdw4TGLb47OwlhqdRsaOcecejIwCXaPRJ7tGah2Y//zaDXnFC9G');

INSERT INTO helpdesk.PERFIL (Pessoa_ID,perfis) VALUES ( 1, 0);

INSERT INTO helpdesk.PERFIL (Pessoa_ID,perfis) VALUES ( 1, 2);

INSERT INTO helpdesk.PERFIL (Pessoa_ID,perfis) VALUES ( 2, 1);

INSERT INTO helpdesk.chamado (id, data_abertura, data_fechamento, observacao, prioridade, status, titulo, cliente_id, tecnico_id) VALUES (1, '2024-10-30 23:14:16.340310', null, 'Teste primeiro chamado', 1, 1, 'Chamado 01', 2, 1);