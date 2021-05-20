package com.seokhan.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplication Annotation으로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정됨.
// 특히, @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 상단에 위치해야만 한다.
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        // 해당 run method로 인해 내장 WAS를 실행.
        // 내장 WAS를 실행하게 되면, 항상 서버에 톰캣을 설치할 필요가 없고, 스프링 부트로 마늗ㄹ어진 Jar파일(실행 가능한 Java 패키징 파일)로 실행됨.
        // 스프링 부트에서는 내장 WAS를 사용하는 것을 권장 => '언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있기 때문.
        SpringApplication.run(Application.class, args);
    }
}
