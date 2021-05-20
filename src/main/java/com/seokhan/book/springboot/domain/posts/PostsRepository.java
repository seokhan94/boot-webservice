package com.seokhan.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 보통 MyBatis에서 Dao라고 불리는 DB Layer 접근자이다.
// JPA에선 Repository라고 부르며 인터페이스로 생성.
// 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성.
// 여기서 주의할 점은 Entity 클래스와 기본 Entity Repository는 함께 위치해야 하는 점이다.
// Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
