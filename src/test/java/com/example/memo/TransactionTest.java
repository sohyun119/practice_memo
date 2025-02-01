package com.example.memo;

import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    MemoRepository memoRepository;

    @Test
    @Transactional //*** insert, update, delete 같은 데이터를 수정할때는 트랜잭션이 무조건 필요하다.(없으면 에러)
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("메모 생성 성공")
    void test1() {
        Memo memo = new Memo();
        memo.setUsername("Robbert");
        memo.setContents("@Transactional 테스트 중!");

        em.persist(memo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }

    @Test
    @Disabled // *** 더 이상 이 메서드는 테스트를 진행하지 않을때
    @DisplayName("메모 생성 실패")
    void test2() {
        Memo memo = new Memo();
        memo.setUsername("Robbie");
        memo.setContents("@Transactional 테스트 중!");

        em.persist(memo);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }

    @Test
    @Disabled
//    @Transactional
//    @Rollback(value = false)
    @DisplayName("트랜잭션 전파 테스트")
    void test3() { //부모 메서드
        //memoRepository.createMemo(em); // 자식 메서드 : 부모 메서드 Transaction에 합쳐짐
        // 부모 메서드에 Transaction이 있기에 트랜잭션 전파가 생겨
        // 부모 메서드까지 모두 실행 후 commit 발생
        // 아니라면 자식메서드 실행 후 commit 후 부모메서드 실행하게 된다.
        System.out.println("테스트 test3 메서드 종료");
    }


}
