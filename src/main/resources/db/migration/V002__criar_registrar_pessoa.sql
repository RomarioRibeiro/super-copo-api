create table pessoa (
codigo bigint primary key auto_increment,
nome varchar(60) not null,
cargo varchar(80) not null,
ativo boolean Not null
);

insert into pessoa (nome, cargo, ativo) values ('Romario Ribeiro', 'Operador', true);
insert into pessoa (nome, cargo, ativo) values ('Simone', 'Operador', true);
insert into pessoa (nome, cargo, ativo) values ('Mara', 'Auxiliar de Operador', true);
insert into pessoa (nome, cargo, ativo) values ('Geraldo', 'Mecanico', true);
insert into pessoa (nome, cargo, ativo) values ('Eliseu', 'Gerente', true);
insert into pessoa (nome, cargo, ativo) values ('João', 'Auxiliar de Operador', true);
