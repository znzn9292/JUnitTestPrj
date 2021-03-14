package com.znzn.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @DisplayName("스터디 만들기 - Tag fast")
//    @Tag("fast")    // Run/Debug Configurations 에서 Test Kind : Tags, Tag expressions: fast 설정 시 해당 테스트만 실행
    @FastTest
    void create_new_tag_fast() {
        System.out.println("fast");
    }

    @DisplayName("스터디 만들기 - Tag slow")
//    @Tag("slow")    // Run/Debug Configurations 에서 Test Kind : Tags, Tag expressions: slow 설정 시 해당 테스트만 실행
    @SlowTest
    void create_new_tag_slow() {
        System.out.println("slow");
    }

    @DisplayName("스터디 만들기 - repeat")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition} / {totalRepetitions}") // 반복 테스트 (반복횟수)
    void repeatTest(RepetitionInfo repetitionInfo){
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기 - ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("스터디 만들기 - ParameterizedTest2")
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
//    @ValueSource(ints = {10, 20, 40})
    @CsvSource({"10, '자바 스터디'", "20, 스피링"})
    void parameterizedTest2(@ConvertWith(StudyConverter.class) Study study) {   // 한개의 아큐먼트 받을 때
//    void parameterizedTest2(ArgumentsAccessor argumentsAccessor){
//    void parameterizedTest2(@AggregateWith(StudyAggregator.class) Study study) {
//        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }
    @Test
    @DisplayName("스터디 만들기 - assumeTrue")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")    // 환경변수 비교 어노테이션 방법
//    @EnabledOnOs({OS.MAC, OS.LINUX})  // OS 별 환경설정
//    @EnabledOnJre({JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})     // java 버전 별 환경설정
    void create_new_study_assume() {
        String test_env = System.getenv("TEST_ENV");
//        System.out.println(test_env);
//        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        // 환경변수 비교 소스 코딩 방법
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("local");
            Study study = new Study(100);
//            assertThat(study.getLimit()).isGreaterThan(0);
        });
    }

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
    @DisplayName("스터디 만들기 - assertTimeout")
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