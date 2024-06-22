# Сервис "VOgorode"

Проект состоит из трёх микросервисов: HandymanService, LandscapeService и RancherService.
Чтобы запустить один из них, необходимо из корневой папки перейти в папку микросервиса и выполнить там команду ```gradlew bootRun``` для Windows, или ```./gradlew bootRun``` для Linux:
```
cd handyman
gradlew bootRun
```
Микросервисы используют следующие порты:
| Service   | Port |
| --------- | ---- |
| Handyman  | 8080 |
| Landscape | 8085 |
| Rancher   | 8090 |

[ДЗ](/docs)

[Инструкция по запуску проекта](dev/README.md)

[Инструкция по сбору метрик](dev/METRICS.md)

[Диаграмма C4](c4/context.md)
