create table producao (
codigo bigint primary key auto_increment,
descricao varchar(80) not null,
data_producao date not null,
data_finalizacao date ,
quantidade decimal(10,2) not null,
observacao varchar(100) ,
codigo_maquina bigint(20) not null,
codigo_pessoa bigint(20) not null,
foreign key (codigo_maquina) references maquina(codigo),
foreign key (codigo_pessoa) references pessoa(codigo)
);

insert into producao (descricao, data_producao, data_finalizacao, quantidade, observacao, codigo_maquina, codigo_pessoa) values ('molde copo eco 600', '2022-09-19', '2022-09-28', '10000', 'incolor', 1, 2);
insert into producao (descricao, data_producao, data_finalizacao, quantidade, observacao, codigo_maquina, codigo_pessoa) values ('molde taça gin', '2022-09-19', '2022-11-28', '20000', 'azul', 2, 2);
insert into producao (descricao, data_producao, data_finalizacao, quantidade, observacao, codigo_maquina, codigo_pessoa) values ('molde copo eco 400', '2022-09-19', '2022-12-28', '50000', 'rosa', 4, 2);
insert into producao (descricao, data_producao, data_finalizacao, quantidade, observacao, codigo_maquina, codigo_pessoa) values ('molde tampa g tradicional', '2022-09-19', '2022-10-28', '150000', 'verde', 3, 5);
insert into producao (descricao, data_producao, data_finalizacao, quantidade, observacao, codigo_maquina, codigo_pessoa) values ('molde long dring', '2022-09-19', '2022-11-02', '120000', 'incolor', 5, 3);