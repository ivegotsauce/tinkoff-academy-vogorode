# HW 2

- Во всех сервисах подключил и настроил gRPC, protobuf
- В LandscapeService реализовал gRPC Client, добавил ручку ```/services/statuses```, выдающую информацию о доступных Handyman и Rancher,
покрыл тестами контроллер
- В Handyman и Rancher реализовал и покрыл тестами gRPC Server, выдающий version и readiness по gRPC, для readiness использовал StatesOfConnectivity
