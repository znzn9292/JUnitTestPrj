package com.znzn.javatest.member;


import com.znzn.javatest.domain.Member;
import com.znzn.javatest.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study study);

    void notify(Member member);
}
