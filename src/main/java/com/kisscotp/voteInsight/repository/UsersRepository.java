package com.kisscotp.voteInsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kisscotp.voteInsight.domain.Users;
import java.util.List;

/*
 * JpaReopsitory가 제공하는 기본 메서드
 * 
 * save() : 객체를 저장하거나 없데이트함
 * findById() : id로 지정한값을 통해 요소를 검색 반환은 Optinal<T>
 * existsById() : id에 해당하는 요소가 존재하는지 확인
 * findAll() : 테이블 안의 모든 요소를 반환
 * count() : 저장된 모든 요소의 수를 반환
 * deleteById() : id에 해당하는 요소를 삭제
 * flush(): 영속성 컨텍스트의 변경을 데이터베이스에 즉시 반영합니다. (뭔지 모르겠슴,,,,)
 * saveAll(Iterable<S> entities): 여러 개의 엔터티를 저장 또는 업데이트합니다
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Users findByStudentid(String studentid);
}
