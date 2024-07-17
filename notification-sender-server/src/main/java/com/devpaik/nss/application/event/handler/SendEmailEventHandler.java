package com.devpaik.nss.application.event.handler;

import com.devpaik.nss.adapter.out.infra.mail.SMTPTransporterException;
import com.devpaik.nss.adapter.out.infra.mail.SendMailPort;
import com.devpaik.nss.application.event.EventPublisher;
import com.devpaik.nss.application.event.SendEmailEvent;
import com.devpaik.nss.application.event.SendResultEvent;
import com.devpaik.nss.dto.EmailMessage;
import com.devpaik.nss.simulation.NotificationSender;
import com.devpaik.nss.simulation.ResponseTypeSelector;
import com.devpaik.nss.simulation.ResultType;
import com.devpaik.nss.simulation.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendEmailEventHandler {
    private final NotificationSender sender = new NotificationSender();

    private final SendMailPort sendMailPort;
    private final EventPublisher eventPublisher;

    public SendEmailEventHandler(SendMailPort sendMailPort, EventPublisher eventPublisher) {
        this.sendMailPort = sendMailPort;
        this.eventPublisher = eventPublisher;
    }

    @Async("eventEmailExecutor")
    @EventListener(SendEmailEvent.class)
    public void sendEmailEventHandler(SendEmailEvent sendEmailEvent) {
        log.debug("# sendEmailEventHandler={}", sendEmailEvent);

        //test simulation code
//        final ResponseTypeSelector selector = new ResponseTypeSelector();
//        String result = sendResponse(selector);
        String result = sendEmail(sendEmailEvent.message());
        eventPublisher.publishEvent(new SendResultEvent(sendEmailEvent.message().eventId(), result));
    }

    private String sendEmail(EmailMessage emailMessage) {
        String result = "";
        try {
            result = sendMailPort.sendMail(emailMessage);
        } catch (SMTPTransporterException e) {
            result = SendResponse.FAIL;
        }
        return result;
    }

    private String sendResponse(ResponseTypeSelector selector) {
        String result = ResultType.FAIL.getResult();
        try {
            SendResponse response = sender.send(selector);
            result = response.resultCode();
        } catch (Exception e) {
            log.error("Send Notification Fail...", e);
        }
        return result;
    }
}
