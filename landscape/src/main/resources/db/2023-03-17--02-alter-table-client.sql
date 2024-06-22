create type client_type_enum as enum('handyman', 'rancher');

create table client_type (
	id integer primary key,
	value client_type_enum
);

insert into client_type values
	(0, 'handyman'),
	(1, 'rancher');

alter table client
 	alter column user_type type integer using
 		case
 			when user_type = 'handyman' then 0
 		else 1
 		end;

alter table client add constraint fk_user_type foreign key (user_type) references client_type(id);