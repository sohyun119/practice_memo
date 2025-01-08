package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoController {

    /*
    * @Autowired 어노테이션을 통해서 IoC 컨테이너에 빈을 등록할 수 있다.
    * 필드주입 / 생성자 주입/ 메서드 주입 / 을 통해 IoC컨테이너에 빈으로 등록된 것들을 주입할 수 있다.
    * 생성자 주입을 가장 많이 쓸 것이며 (객체의 불변성을 지켜줄 수 있기에)
    * ㄴ 생성자가 하나이면 @Autowired 를 생략가능하다.
    *
    * */
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {

        return memoService.createMemo(requestDto);

    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {

        return memoService.getMemos();

    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {

        return memoService.updateMemo(id, requestDto);

    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {

        return memoService.deleteMemo(id);

    }


}