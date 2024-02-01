package com.kisscotp.voteInsight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kisscotp.voteInsight.domain.Board;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
   
    //글 작성 뷰
    @GetMapping("/board/write") 
    public String boardWriteForm(){
        return "/boardWrite";
    }

    //글작성
    @PostMapping("/board/writepro")
    public String boardwritePro(Board board){ //String title, String content
        System.out.println("제목: " +board.getTitle());
        System.out.println("내용: " +board.getContents());
        return "/boardList";
    }


}
