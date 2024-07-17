package com.devpaik.nss.adapter.out.infra.mail;


import com.devpaik.nss.dto.EmailMessage;
import com.devpaik.nss.simulation.SendResponse;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * gmail smtp 테스트할 경우 https://support.google.com/mail/answer/185833?hl=ko
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class SMTPTransporter implements SendMailPort {

	private final JavaMailSender mailSender;

	@Override
	public String sendMail(EmailMessage message) {
		try {
		    MimeMessage mime = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mime);

			helper.setFrom(message.senderEmail());
			helper.setTo(new InternetAddress(message.receiverEmail()));

			helper.setSubject(message.title());
			helper.setText(message.contents(), true);

			mailSender.send(helper.getMimeMessage());
			return SendResponse.SUCCESS;
		} catch (Exception e) {
			throw new SMTPTransporterException(e.getMessage());
		}
	}
}
