package com.sb.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sb.application.member.MemberResponse;
import com.sb.application.member.MemberService;
import com.sb.infra.auth.JwtTokenProvider;
import com.sb.support.IntegrationTestSupport;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;

class MemberControllerTest extends IntegrationTestSupport {

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("내 정보를 조회한다.")
    void getMyInfo() throws Exception {
        MemberResponse response = new MemberResponse(
                1L,
                "example@gmail.com",
                "alstn113",
                "MINSU KIM",
                "https://example.com/image.png"
        );
        BDDMockito.given(jwtTokenProvider.getMemberId(any()))
                .willReturn(1L);
        BDDMockito.given(memberService.getMemberById(anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/api/v1/members/me")
                                .cookie(new Cookie("token", "mock_token"))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
