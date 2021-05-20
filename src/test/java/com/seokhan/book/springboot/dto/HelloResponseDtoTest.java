package com.seokhan.book.springboot.dto;

import com.seokhan.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_unit_test(){
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // assertThat
        // assertj라는 테스트 검증 라이브러리의 검증 메소드입니다.
        // 검증하고 싶은 대상을 메소드 인자로 받는다.
        // 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
