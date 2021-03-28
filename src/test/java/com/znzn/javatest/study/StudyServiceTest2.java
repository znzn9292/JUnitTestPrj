package com.znzn.javatest.study;

import com.znzn.javatest.domain.Member;
import com.znzn.javatest.domain.Study;
import com.znzn.javatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest2 {

        @Test
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("znzn9292@gmail.com");

        Study study = new Study(10, "테스트");

        // TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        // TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        // notify 를 한번(times로 설정) 실행 했는지 테스트
        // 실행 하지 않았다면 에러 발생
        verify(memberService, times(1)).notify(study);
        verify(memberService, times(1)).notify(member);
        //
        verify(memberService, never()).validate(any());

        // 언제 어떤게 먼저 호출 되었는지 테스트
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);

        // 더이상 추가적인 액션이 발생되면 안된다 테스트
                verifyNoMoreInteractions(memberService);
//        studyService.createNewStudy(1L, study);
//
//        assertNotNull(study.getOwner());
//        assertEquals(member, study.getOwner());

        }


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