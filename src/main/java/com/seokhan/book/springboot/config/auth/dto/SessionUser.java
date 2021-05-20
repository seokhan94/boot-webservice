package com.seokhan.book.springboot.config.auth.dto;

import com.seokhan.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// SessionUser에는 인증된 사용자 정보만 필요로함.
// 그 외에 필요한 정보들은 없으니 name, email, picture만 필드로 선언.
// 만약, User 클래스를 그대로 사용했다면 직렬화를 구현하지 않았다는 에러가 생김.
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
