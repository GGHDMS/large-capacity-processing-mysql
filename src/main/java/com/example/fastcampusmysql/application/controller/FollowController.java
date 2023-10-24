package com.example.fastcampusmysql.application.controller;

import com.example.fastcampusmysql.application.usercase.CreateFollowMemberUseCase;
import com.example.fastcampusmysql.application.usercase.GetFollowingMemberUseCase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final CreateFollowMemberUseCase createFollowMemberUseCase;
    private final GetFollowingMemberUseCase getFollowingMemberUseCase;

    @PostMapping("/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUseCase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> get(@PathVariable Long fromId) {
        return getFollowingMemberUseCase.execute(fromId);
    }
}
