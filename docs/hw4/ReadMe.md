# HW4

- Добавил сохранение состояния БД
  - Прописал volume для postgres
  - Создал тестовую таблицу, пересоздал контейнер с бд, состояние сохранилось
- Запустил БД с персистентным хранилищем в Minikube
  - В файле [postgres-storage.yml](../../dev/kube/postgres-storage.yml) описано создание volume
  - В файле [postgres.yml](../../dev/kube/postgres.yml) описано создание БД
- Запустил сервисы Handyman, Rancher и Landscape в Minikube
  - Загрузил локальные образы из докера при помощи ```minikube image load```
  - Запустил всё при помощи ```kubectl create -f```
- Подробный запуск Minikube и всех сервисов можно посмотреть в [видео](https://youtu.be/d8JT9K3j4os)