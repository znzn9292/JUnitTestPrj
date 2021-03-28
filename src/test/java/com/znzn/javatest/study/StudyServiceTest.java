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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

        @Test
    void createStudyService(@Mock MemberService memberService,
                            @Mock StudyRepository studyRepository) {
            StudyService studyService = new StudyService(memberService, studyRepository);
            assertNotNull(studyService);

            Member member = new Member();
            member.setId(1L);
            member.setEmail("znzn9292@gmail.com");

            // Mock 객체로 Stubbing
            when(memberService.findById(any())).thenReturn(Optional.of(member));

            when(memberService.findById(any()))
                    .thenReturn(Optional.of(member))    // 첫번째 호출 되면 member return
                    .thenThrow(new RuntimeException())  // 두번째 호출 되면 RuntimeException
                    .thenReturn(Optional.empty());      // 세번째 호출 되면 빈 값 return

            Optional<Member> byId = memberService.findById(1L);
            assertEquals("znzn9292@gmail.com", byId.get().getEmail());

            assertThrows(RuntimeException.class, () -> {
                memberService.findById(2L);
            });

            assertEquals(Optional.empty(), memberService.findById(3L));


            assertEquals("znzn9292@gmail.com", memberService.findById(1L).get().getEmail());
            assertEquals("znzn9292@gmail.com", memberService.findById(2L).get().getEmail());

            when(memberService.findById(1L)).thenThrow(new RuntimeException());

            doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

            assertThrows(IllegalArgumentException.class, () -> {
                memberService.validate(1L);
            });

            memberService.validate(1L);

    }


    // Mock 객체 생성 방법 1 - Mokito 인스턴트 이용
    @Test
    void createStudyService() {
//        MemberService memberService = mock(MemberService.class);
//        StudyRepository studyRepository = mock(StudyRepository.class);
//        StudyService studyService = new StudyService(memberService, studyRepository);
    }
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