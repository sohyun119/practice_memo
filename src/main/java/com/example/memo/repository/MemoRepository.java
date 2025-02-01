package com.example.memo.repository;

import com.example.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository -> extends 했기때문에 @Repository 따로 달지 않아도 달아짐
public interface MemoRepository extends JpaRepository<Memo, Long> {



}
