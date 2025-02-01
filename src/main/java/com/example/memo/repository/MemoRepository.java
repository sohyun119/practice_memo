package com.example.memo.repository;

import com.example.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository -> extends 했기때문에 @Repository 따로 달지 않아도 달아짐
public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByOrderByModifiedAtDesc();
    // ㄴ *** 메서드 이름 규칙 패턴으로 선언만해도 SQL작성 없이 사용할 수 있다.

    //List<Memo> findAllByUserName(String username);
}
