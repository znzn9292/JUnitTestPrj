package com.znzn.javatest.study;

import com.znzn.javatest.domain.Study;
import com.znzn.javatest.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class StudyServicePracticeTest {

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        // Given
        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");

        // TODO studyRepository Mock 객체의 save 메소드를호출 시 study를 리턴하도록 만들기.
        when(studyRepository.save(study)).thenReturn(study);    // Mockito
        given(studyRepository.save(study)).willReturn(study);   // BDDMockito

        // When
        studyService.openStudy(study);
        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(study.getStatus(), StudyStatus.OPENED, "Study Status Not OPENED");

        // TODO study의 openedDataTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());

        // TODO memberService의 notify(study)가 호출 됐는지 확인.
        then(memberService).should().notify(study);
        verify(memberService).notify(study);
    }

}
