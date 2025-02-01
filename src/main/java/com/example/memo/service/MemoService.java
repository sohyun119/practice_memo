package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

   // @Autowired 가 생성자1개일때는 생략가능할 수 있게 되었다.
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }


    public MemoResponseDto createMemo(MemoRequestDto requestDto) {

        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {

        return memoRepository.findAll().stream().map(MemoResponseDto::new).toList();

    }


    @Transactional  // ** 없으면 제대로 변경감지가 되지 않아서 update가 되지 않는다.
    public Long updateMemo(Long id, MemoRequestDto requestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        // memo 내용 수정
        memo.update(requestDto);

        return id;

    }



    public Long deleteMemo(Long id) {

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        // memo 삭제
        memoRepository.delete(memo);

        return id;

    }

    private Memo findMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        ); // memo가 null값일때는 Throw 를 던짐 (memoRepository.findById는 반환타입이 Optional임
    }
}
