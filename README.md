# 알림 서버

알림 front 서버와 알림 전송 서버로 구성

* docker RabbitMQ 설치

```
docker pull rabbitmq
```

* rabbitmq_management enable

```
docker exec rabbitmq rabbitmq-plugins enable rabbitmq_management
```

* rabbitmq 실행
```
docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq rabbitmq
```
