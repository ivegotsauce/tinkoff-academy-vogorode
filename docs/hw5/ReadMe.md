# HW 5

- Настроил Liquibase
- Создал миграцию, создающую таблицу пользователей
- Создал миграцию, заменяющую тип колонки с типом пользователя при помощи ```alter column type```
- Написал хранимую процедуру, порционно добавляющую значения в новую колонку

Вызов хранимой процедуры:
```
db=# call migrate_user_types(8, 2);
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 8 rows. Migration paused for 2 sec.
NOTICE:  2023-03-17 18:59:36.145169+00 - Committed 4 rows. Migration paused for 2 sec.
CALL
```