package com.devpaik.nfs.notification.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.devpaik.nfs.notification.adapter.in.web.dto.RegisterForNotificationSendRequest;
import com.devpaik.nfs.notification.application.port.in.RegisterForSMSSendUseCase;
import com.devpaik.nfs.notification.application.port.in.command.RegisterForSMSSendCommand;
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

@WebMvcTest(controllers = RegisterForSMSSendController.class)
class RegisterForSMSSendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RegisterForSMSSendUseCase registerForSMSSendUseCase;

    @MockBean
    private NotificationEventPort notificationEventPort;

    @DisplayName("알림 SMS 등록 테스트")
    @Test
    void registerForSMSSendTest() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("sender", "01012341111");
        multiValueMap.add("receiver", "01012345555");
        multiValueMap.add("title", "안녕하세요");
        multiValueMap.add("contents", "테스트입니다.");
        multiValueMap.add("deliveryReqDtm", "202405111120");
        multiValueMap.add("client", "web");

        mockMvc.perform(post("/api/v1/notifications/send/sms")
                        .content(objectMapper.writeValueAsString(multiValueMap.toSingleValueMap()))
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        BDDMockito.verify(registerForSMSSendUseCase).registerForSMSSend(BDDMockito.any());

        RegisterForNotificationSendRequest request = new RegisterForNotificationSendRequest
                ("01012341111", "01012345555", "안녕하세요", "테스트입니다.",
                        "202405111120", "web");

        RegisterForSMSSendCommand command = RegisterForSMSSendCommand.of(request);

        registerForSMSSendUseCase.registerForSMSSend(command);
    }
}