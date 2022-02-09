insert into tb_armazem (id, descricao, nome) values (null, 'Armazem grande', 'Armazem 1');
insert into tb_setor (id, armazem_id, nome, tipo, capacidade_total, capacidade_atual) values (null, 1, 'Setor A', 0, 1000.00, 800.0);
insert into tb_representante (id, armazem_id, nome) values (null, 1, 'José Filho');
insert into tb_produto (id, conservacao, nome, volume_uni) values (null, 0, 'Carne', 20.0);
insert into tb_lote (id, data_fabricacao, data_validade, produto_id, quantidade_atual, quantidade_inicial, temperatura_atual, temperatura_minima) values (null, '2018-05-07T15:20:45.765Z', '2022-05-07T15:20:45.765Z', 1, 20, 20, 5.0, 7.0);
insert into tb_estoque (id, data, representante_id, setor_id) values (null,'2021-12-07T05:33:00Z' , 1, 1);
insert into tb_estoque_lotes (registro_de_estoques_id, lotes_id) values (1, 1);

insert into tb_produto (id, conservacao, nome, volume_uni) values (null, 0, 'Carne', 20.0);
insert into tb_lote (id, data_fabricacao, data_validade, produto_id, quantidade_atual, quantidade_inicial, temperatura_atual, temperatura_minima) values (null, '2018-05-07T15:20:45.765Z', '2022-03-08T15:20:45.765Z', 1, 40, 40, 5.0, 7.0);
insert into tb_lote (id, data_fabricacao, data_validade, produto_id, quantidade_atual, quantidade_inicial, temperatura_atual, temperatura_minima) values (null, '2018-05-07T15:20:45.765Z', '2022-04-07T15:20:45.765Z', 1, 30, 30, 5.0, 7.0);
insert into tb_estoque (id, data, representante_id, setor_id) values (null,'2021-12-07T05:33:00Z' , 1, 1);
insert into tb_estoque_lotes (registro_de_estoques_id, lotes_id) values (2, 2);

insert into tb_estoque (id, data, representante_id, setor_id) values (null,'2021-12-07T05:33:00Z' , 1, 1);
insert into tb_estoque_lotes (registro_de_estoques_id, lotes_id) values (3, 3);

insert into tb_vendedor (id, nome) values (null, 'João Primo');
insert into tb_anuncio (id, descricao, lote_id, preco, titulo, vendedor_id) values (null, 'Carne de primeira', 1, 100.00, 'Alcatra', 1);

insert into tb_anuncio (id, descricao, lote_id, preco, titulo, vendedor_id) values (null, 'Carne de primeira', 2, 55.00, 'Patinho', 1);

insert into tb_comprador (id, carrinho_id, nome) values (null, null, 'Maria Green');

insert into users (username, password, enabled) values('admin', '$2a$10$FnSsqc9hnfZ.HLR0HDZ0gOGbNnd1yit.sZitZVibdCgle1E6cwL4a', '1');
insert into users (username, password, enabled) values('andre', '$2a$10$FnSsqc9hnfZ.HLR0HDZ0gOGbNnd1yit.sZitZVibdCgle1E6cwL4a', '1');
insert into users (username, password, enabled)  values('kenyo', '$2a$10$FnSsqc9hnfZ.HLR0HDZ0gOGbNnd1yit.sZitZVibdCgle1E6cwL4a', '1');
insert into users (username, password, enabled)  values('maria', '$2a$10$FnSsqc9hnfZ.HLR0HDZ0gOGbNnd1yit.sZitZVibdCgle1E6cwL4a', '1');

insert into perfil(nome) values ('ADMIN');
insert into perfil(nome) values ('CUSTOMER');
insert into perfil(nome) values ('SELLER');
insert into perfil(nome) values ('REPRESENTANTE');

insert into users_perfis (usuario_username, perfis_id) values ('admin', 1);
insert into users_perfis (usuario_username, perfis_id) values ('andre', 2);
insert into users_perfis (usuario_username, perfis_id) values ('kenyo', 4);
insert into users_perfis (usuario_username, perfis_id) values ('maria', 3);

-- insert into tb_carrinho (id, compra_id, comprador_id) values (null, null, 1);
-- update tb_comprador set carrinho_id = 1 where id = 1;
-- insert into tb_carrinho_anuncio (id, anuncio_id, carrinho_id, quantidade) values (null, 1, 1, 3);
--insert into tb_compra (id, carrinho_id, data) values (null, 1, '2018-10-22T17:45:45.765Z');