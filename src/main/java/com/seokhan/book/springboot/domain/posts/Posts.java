package com.seokhan.book.springboot.domain.posts;

import com.seokhan.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
// @NoArgsConstructor : lombok Annotation
// 기본 생성자 자동 추가
// public Posts() {}와 같은 효과
@NoArgsConstructor
// @Entity : JPA Annotation
// 테이블과 링크될 클래스.
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다.
// (ex) SalesManager.java -> sales_manager table
// Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
// 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 한다.
// setter가 없는 이 상황에서 어떻게 값을 채워 DB에 삽입해야하나 ??
// 기본적인 구조는 생성자를 통해 최종값을 채운 후 DB에 삽입하는 것이며, 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제로 한다.
//
@Entity
public class Posts extends BaseTimeEntity {

    // 해당 테이블의 PK필드를 나타낸다.
    @Id
    // PK의 생성 규칙을 나타낸다.
    // 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용.
    // 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex:title), 타입을 TEXT로 변경하고 싶거나(ex:content)등의 경우에 사용된다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // @Builder
    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌드에 포함
    // 생성자나 빌더나 생성 시점에 값을 채워주는 역할은 똑같다.
    // 다만, 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 없다.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
