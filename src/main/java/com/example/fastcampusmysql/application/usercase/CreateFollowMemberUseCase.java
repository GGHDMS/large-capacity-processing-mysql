package com.example.fastcampusmysql.application.usercase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUseCase {

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService ;

    public void execute(Long fromMemberId, Long toMemberId) {
        /*
            1. 입력받은 memberId 로 회원 조회
            2. FollowWriteService.create()
         */
        MemberDto fromMember = memberReadService.getMember(fromMemberId);
        MemberDto toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
