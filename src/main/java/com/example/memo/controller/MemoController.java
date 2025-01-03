package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    // Create
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
        // ㄴ *post man 에서 Body 클릭 후 raw 클릭 후 JSON형태로 보내서 확인 가능
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // Memo Max id Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    // Read
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        // Map To List  ****
        List<MemoResponseDto> responseList = memoList.values().stream()
                .map(MemoResponseDto::new).toList();

        return responseList;
    }

    // update
    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){

        // 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)){
            // 해당 메모 가져오기
            Memo memo = memoList.get(id);

            // memo 수정
            memo.update(requestDto);
            return memo.getId();
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // delete
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        // 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)){
            // 해당 메모 삭제
            memoList.remove(id);
            return id;
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }

    }

}
