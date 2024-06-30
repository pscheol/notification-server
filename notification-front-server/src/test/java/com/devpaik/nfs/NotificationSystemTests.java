package com.devpaik.nfs;

import com.devpaik.nfs.notification.adapter.in.web.dto.NotificationSendQueryResponse;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendResponse;
import com.devpaik.nfs.notification.exceptions.ServiceCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotificationSystemTests {


    @Autowired
    private TestRestTemplate restTemplate;

    private static final HttpHeaders headers = new HttpHeaders();
    private static final String PUSH = "push";
    private static final String SMS = "sms";
    private static final String EMAIL = "email";

    @BeforeEach
    public void init() {
        headers.add("Content-Type", "application/json");
    }

    @DisplayName("Push 알람 전송 요청 System 테스트")
    @Test
    void registerForNotificationPushSendResponseTest()  {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("sender", "mu");
        map.put("receiver", "pscheol");
        map.put("title", "안녕하세요");
        map.put("contents", "테스트입니다.");
        map.put("deliveryReqDtm", "202405111120");
        map.put("client", "app");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<RegisterForNotificationSendResponse> response = postExchange(PUSH, request);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).code()).isEqualTo(ServiceCode.OK.getCode());
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).message()).isEqualTo(ServiceCode.OK.getMsg());
    }


    @DisplayName("SMS 알람 전송 요청 System 테스트")
    @Test
    void registerForNotificationSMSSendResponseTest()  {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("sender", "01012341234");
        map.put("receiver", "01012345555");
        map.put("title", "안녕하세요");
        map.put("contents", "테스트입니다.");
        map.put("deliveryReqDtm", "202405111120");
        map.put("client", "app");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<RegisterForNotificationSendResponse> response = postExchange(SMS, request);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).code()).isEqualTo(ServiceCode.OK.getCode());
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).message()).isEqualTo(ServiceCode.OK.getMsg());
    }

    @DisplayName("Email 알람 전송 요청 System 테스트")
    @Test
    void registerForNotificationEmailSendResponseTest()  {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("sender", "test@gmail.com");
        map.put("receiver", "pscheol@gmail.com");
        map.put("title", "안녕하세요");
        map.put("contents", "테스트입니다.");
        map.put("deliveryReqDtm", "202406111120");
        map.put("client", "app");


        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<RegisterForNotificationSendResponse> response = postExchange(EMAIL, request);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).code()).isEqualTo(ServiceCode.OK.getCode());
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).message()).isEqualTo(ServiceCode.OK.getMsg());
    }

    @Sql("classpath:notification_insert.sql")
    @DisplayName("알람 전송 완료 목록 System 테스트")
    @Test
    void getNotificationSuccessList()  {

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<NotificationSendQueryResponse> response = restTemplate.exchange(
                "/api/v1/notifications/send?page=1&limit=10",
                HttpMethod.GET,
                request, NotificationSendQueryResponse.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    private ResponseEntity<RegisterForNotificationSendResponse> postExchange(String uri, HttpEntity<Map<String, String>> request) {
        ResponseEntity<RegisterForNotificationSendResponse> response = restTemplate.exchange(
                "/api/v1/notifications/send/" + uri,
                HttpMethod.POST,
                request, RegisterForNotificationSendResponse.class);
        return response;
    }
}
