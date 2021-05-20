package com.seokhan.book.springboot.service.posts;

import com.seokhan.book.springboot.domain.posts.Posts;
import com.seokhan.book.springboot.domain.posts.PostsRepository;
import com.seokhan.book.springboot.web.dto.PostsListResponseDto;
import com.seokhan.book.springboot.web.dto.PostsResponseDto;
import com.seokhan.book.springboot.web.dto.PostsSaveRequestDto;
import com.seokhan.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없다.
    // 이게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    // 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다. 일종의 논리적 개념이라고 보면 되며, JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐이다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        // JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용한다.
        // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수 도 있다.
        // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.
        postsRepository.delete(posts);
    }

    // 트랜잭션 어노테이션에 (readOnly=true)옵션을 추가해주면 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회 속도가 개선된다.
    // 따라서 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // 해당 코드는 .map(posts -> new PostsListResponseDto(posts))와 같다.
                .collect(Collectors.toList());
    }


    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID = " + id));

        return new PostsResponseDto(entity);
    }
}
