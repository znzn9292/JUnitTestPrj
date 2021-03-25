package com.znzn.javatest.study;

import com.znzn.javatest.domain.Member;
import com.znzn.javatest.domain.Study;
import com.znzn.javatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    // Mock 객체 생성 방법 1 - Mokito 인스턴트 이용
//    @Test
//    void createStudyService() {
//        MemberService memberService = mock(MemberService.class);
//        StudyRepository studyRepository = mock(StudyRepository.class);
//    }
    //------------------------------------//

    // Mock 객체 생성 방법 2 - @ExtendWith, @Mock 어노테이션을 활용한 멤버변수 이용
//    @Mock MemberService memberService;
//    @Mock StudyRepository studyRepository;
//
//    @Test
//    void createStudyService() {
//        StudyService studyService = new StudyService(memberService, studyRepository);
//        assertNotNull(studyService);
//    }
    //------------------------------------//

    // Mock 객체 생성 방법 3 - @ExtendWith, @Mock 어노테이션을 활용한 매개변수로 이용
//    @Test
//    void createStudyService(@Mock MemberService memberService,
//                            @Mock StudyRepository studyRepository) {
//        StudyService studyService = new StudyService(memberService, studyRepository);
//        assertNotNull(studyService);
//    }
    //------------------------------------//
}