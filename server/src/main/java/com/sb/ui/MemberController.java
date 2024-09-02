package com.sb.ui;

import com.sb.ui.auth.Auth;
import com.sb.application.auth.Accessor;
import com.sb.application.member.MemberResponse;
import com.sb.application.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members/me")
    public ResponseEntity<MemberResponse> getMyInfo(@Auth Accessor accessor) {
        MemberResponse response = memberService.getMemberById(accessor.id());

        return ResponseEntity.ok(response);
    }
}
