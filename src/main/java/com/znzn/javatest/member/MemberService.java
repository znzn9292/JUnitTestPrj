package com.znzn.javatest.member;


import com.znzn.javatest.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

}
