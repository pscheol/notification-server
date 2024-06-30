# 알림 전송 서버

## 1. 디렉토리 구조

---

```tree
nss
.
├── NotificationSenderServerApplication.java
├── common
│   ├── PayloadConvertException.java
│   └── Serializer.java
├── config
│   ├── NotificationSenderConfig.java
│   └── RabbitMQConfig.java
├── consumer
│   ├── SendEmailConsumer.java
│   ├── SendPushConsumer.java
│   └── SendSMSConsumer.java
├── dto
│   ├── EmailMessage.java
│   ├── MessageMapper.java
│   ├── PushMessage.java
│   ├── ResultMessage.java
│   └── SMSMessage.java
├── event
│   ├── EventPublisher.java
│   ├── SendEmailEvent.java
│   ├── SendPushEvent.java
│   ├── SendSMSEvent.java
│   └── handler
│       └── SendEventHandler.java
├── producer
│   ├── FrontNotificationProducer.java
│   ├── SendFrontNotificationByFail.java
│   └── SendFrontNotificationBySuccess.java
└── simulation
    ├── LatencyType.java
    ├── NotificationSender.java
    ├── ResponseType.java
    ├── ResponseTypeSelector.java
    ├── ResultType.java
    └── SendResponse.java


```