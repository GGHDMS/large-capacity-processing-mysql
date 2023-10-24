package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @DisplayName("회원은 닉넴임을 변경할 수 있다.")
    @Test
    void testChangeName() {
        Member member = MemberFixtureFactory.create();
        String expected = "pnu";

        member.changeNickname(expected);

        assertThat(member.getNickname()).isEqualTo(expected);
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    void testNicknameMaxLength() {
        Member member = MemberFixtureFactory.create();
        String expected = "toooooooooolong";

        Assertions.assertThatThrownBy(() -> member.changeNickname(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }



}
