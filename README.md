# Сборка и запуск

mvn spring-boot:run

# БД

Сделана реализация для H2 (profile = demo) и PostgreSQL (profile = prod).

Переключение в application.yml.

Сейчас включен профиль demo, подключиться к H2 в рантайме можно через строку 
```
jdbc:h2:file:C:\путь к проекту\t2webchat;AUTO_SERVER=TRUE
```

# Тестирование

Приложение доступно в браузере по адресу:
```
http://localhost:8080/
```
<img src="https://raw.githubusercontent.com/rehabrrr/2025_java_websockets_chat_T2/10adcb247cf9cb5ea037ac33a67fdae963492bc0/screen1.png">

# Docker

Dockerfile
```
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/webchat-0.0.1-SNAPSHOT.jar webchat.jar
ENTRYPOINT ["java","-jar","/webchat.jar"]
```
Собрать Docker образ:
```
docker build -t webchat .
docker run -p 8080:8080 webchat
```


