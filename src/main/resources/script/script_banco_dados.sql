create table if not exists tarefa.tarefas (

	id_tarefa integer auto_increment primary key,
	nome_tarefa varchar (50) not null,
	tipo_tarefa varchar (50) not null,
	realizada boolean not null default false comment 'true- realizada; false - não realizada',
	id_item int not null,
	foreign key (id_item) references tarefa.item (id_item)

)



create table if not exists tarefa.item (
	id_item integer auto_increment primary key,
	descricao varchar (255) not null,
	realizado boolean bit bykk default comment 'true- realizada; false - não realizada'
	
)