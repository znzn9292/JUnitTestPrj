package com.znzn.javatest;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기 - Basic")
    void create_new_study_basic() {
        Study study = new Study(-10);
        assertNotNull(study);

        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 DRAFT 상태다.");
        //assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 DRAFT 상태다.");

        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다." );
    }

    @Test
    @DisplayName("스터디 만들기 - assertAll")
    void create_new_study_asserAll() {
        Study study = new Study(-10);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 처음 만들면 DRAFT 상태다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다." )
        );
    }

    @Test
    @DisplayName("스터디 만들기 - assertThrows")
    void create_new_study_assertThrows() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();

        assertEquals("limit은 0보다 커야 한다", message);
    }

    @Test
    @DisplayName("스터디 만들기 - assertThrows")
    void create_new_study_assertTimeout() {
        // 100 ms 가 지나면 테스트가 중지 (주의해서 사용)
//        assertTimeoutPreemptively(Duration.ofMillis(100), () ->{
//            new Study(10);
//            Thread.sleep(300);
//        });

        assertTimeout(Duration.ofMillis(300), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    // @Disabled : 테스트를 ignore 시킴
//    @Disabled
    @Test
    @DisplayName("스터디 만들기3")
    void create_new_study_again() {
        System.out.println("create1");
    }

    // 모든 테스트 실행 이전에 딱 한번만 실행됨
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    // 모든 테스트 실행 이후에 딱 한번만 실행됨
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    // 각각 테스트 실행 이전에 실행됨
    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    // 각각 테스트 실행 이후에 실행됨
    @AfterEach
    void afterEach() {
        System.out.println("After each");
    }
}