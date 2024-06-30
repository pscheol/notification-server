package com.devpaik.nfs.notification.adapter.in.web;

import com.devpaik.nfs.notification.application.port.out.GetNotificationQueryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GetNotificationSendQueryController.class)
class GetNotificationSendQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetNotificationQueryPort getNotificationQueryPort;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("알림 완료 목록 테스트")
    @Test
    void getNotificationListTest() throws Exception {

        mockMvc.perform(get("/api/v1/notifications/send?page=1&limit=10")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        verify(getNotificationQueryPort).totalNotificationSuccess();

        verify(getNotificationQueryPort).selectNotificationSuccessList(1, 10);

    }


}