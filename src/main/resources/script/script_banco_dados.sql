create schema tarefa;

use tarefa;

create table if not exists tarefa.usuario (
	id_usuario integer auto_increment primary key,
    nome varchar(255) not null,
    cpf varchar(11) not null,
    email varchar(50) not null,
    data_nascimento DATE NOT NULL,
	login varchar(20) not null,
    senha varchar(12) not null
);

create table if not exists tarefa.tarefas (
	id_tarefa integer auto_increment primary key,
    id_usuario int not null,
	nome_tarefa varchar (50) not null,
	tipo_tarefa varchar (50) not null,
	realizada boolean not null default false comment 'true- realizada; false - não realizada',
    isTemplate boolean not null default false comment 'true - usar template; false - não usar template',
	foreign key (id_usuario) references tarefa.usuario (id_usuario)
);

create table if not exists tarefa.item (
	id_item integer auto_increment primary key,
	descricao varchar (255) not null,
	realizado boolean not null default false comment 'true- realizada; false - não realizada',
	id_tarefa int not null,
    foreign key (id_tarefa) references tarefa.tarefas (id_tarefa)
);