# HW1
- Используя Springboot Initializr развернул три проекта: [HandymanService](./handyman), [LandscapeService](./landscape) и [RancherService](./rancher).
- В каждом помапил info на ```/actuator/info``` и prometheus на ```/metrics```.
- В каждом сервисе реализовал Controller для ручек ```/system/liveness``` и ```/system/readiness```.
- Написал тесты на эндпоинты, используя паттерн GWT.
