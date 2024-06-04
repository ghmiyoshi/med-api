INSERT INTO tb_usuarios (id, login, senha)
VALUES (1, 'admin', '$2a$10$9ZyGp5KIbsPrFht.ks0kX' ||
                    '.MjOQZG39xLof7HdqBp8bKka5UiFGasW');
INSERT INTO tb_usuarios (id, login, senha)
VALUES (2, 'user', '$2a$10$9ZyGp5KIbsPrFht.ks0kX.MjOQZG39xLof7HdqBp8bKka5UiFGasW');

INSERT INTO tb_perfis (id, nome)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO tb_perfis (id, nome)
VALUES (2, 'ROLE_USER');

INSERT INTO tb_usuarios_perfis (usuario_id, perfil_id)
VALUES (1, 1);
INSERT INTO tb_usuarios_perfis (usuario_id, perfil_id)
VALUES (1, 2);
INSERT INTO tb_usuarios_perfis (usuario_id, perfil_id)
VALUES (2, 2);

INSERT INTO tb_medicos (nome, email, crm, especialidade, logradouro, bairro, cep, complemento,
                        numero, uf, cidade, created_by,
                        last_modified_by, creation_date, last_modified_date, telefone, ativo)
VALUES ('Gabriel', 'admin@admin.com', '12323', 'ORTOPEDIA', 'rua 1', 'bairro', '12345678',
        'complemento', '1', 'DF',
        'Brasilia', 'system', 'system', NOW(), NOW(), '1234-5678', 1);
