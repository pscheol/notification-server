package com.devpaik.nfs.notification.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.application.port.in.RegisterForPushSendUseCase;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForPushSendCommand;
import com.devpaik.nfs.notification.application.port.out.NotificationEventPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RegisterForPushSendController.class)
class RegisterForPushSendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RegisterForPushSendUseCase registerForPushSendUseCase;

    @MockBean
    private NotificationEventPort notificationEventPort;

    @DisplayName("Push 등록 테스트")
    @Test
    void registerForPushSendTest() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("sender", "test");
        multiValueMap.add("receiver", "test123123123");
        multiValueMap.add("title", "안녕하세요");
        multiValueMap.add("contents", "테스트입니다.");
        multiValueMap.add("deliveryReqDtm", "202405111120");
        multiValueMap.add("client", "app");

        mockMvc.perform(post("/api/v1/notifications/send/push")
                        .content(objectMapper.writeValueAsString(multiValueMap.toSingleValueMap()))
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        BDDMockito.verify(registerForPushSendUseCase).registerForPushSend(BDDMockito.any());

        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("test", "test123123123", "안녕하세요", "테스트입니다.",
                        "202405111120", "app");

        RegisterForPushSendCommand command = RegisterForPushSendCommand.of(request);

        registerForPushSendUseCase.registerForPushSend(command);
    }
}