create table consultas(

  id bigint not null auto_increment,
  profesor_id bigint not null,
  alumno_id bigint not null,
  fecha datetime not null,

  primary key(id),
  constraint fk_consultas_profesor_id foreign key(profesor_id) references profesores(id),
  constraint fk_consultas_alumno_id foreign key(alumno_id) references alumnos(id)

);