create procedure migrate_user_types(batch_size integer, time_interval integer)
language plpgsql
as $$
declare
	total_rows integer;
	table_size integer;
begin
	alter table client add column user_type_id integer;
	alter table client add constraint fk_user_type_id foreign key (user_type_id) references client_type(id);
	perform * from client where user_type_id is null;
	get diagnostics table_size := row_count;
	loop
		exit when table_size = 0; 
		update client set user_type_id = client_type.id from client_type
		where client.user_type = client_type.value::text and client.id in (select id from client where user_type_id is null limit batch_size);
		get diagnostics total_rows := row_count;
		raise notice '% - Committed % rows. Migration paused for % sec.', now(), total_rows, time_interval;
		table_size = table_size - total_rows;
		perform pg_sleep(time_interval);
	end loop;
end $$;