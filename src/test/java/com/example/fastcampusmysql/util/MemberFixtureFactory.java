package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {

    static public Member create() {
        EasyRandomParameters param = new EasyRandomParameters()
                .seed(System.currentTimeMillis());
        return new EasyRandom(param).nextObject(Member.class);
    }
}
