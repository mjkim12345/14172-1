package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.MemberDto;
import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
import com.back.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;

    public record MemberJoinReqBody(
            @NotBlank
            @Size(min = 2, max = 20)
            String username,
            @NotBlank
            @Size(min = 2, max = 20)
            String password,
            @NotBlank
            @Size(min = 2, max = 20)
            String name
    ) {
    }

    @PostMapping("/join")
    @Transactional
    @Operation(summary = "회원가입")
    public RsData<MemberDto> join(
            @RequestBody @Valid MemberJoinReqBody form
    ) {
        Member member = memberService.join(form.username, form.password, form.name);

        return new RsData<>(
                "201-1",
                "%s님 환영합니다. 회원가입이 완료되었습니다.".formatted(member.getName())
        );
    }

    @GetMapping("/me")
    @Transactional
    @Operation(summary = "회원 조회")
    public RsData<MemberDto> me(
            @RequestParam int actorId
    ) {
        Member member = memberService.findById(actorId);

        return new RsData<>(
                "200",
                "내 정보 조회 성공",
                new MemberDto(member)
        );
    }

}
