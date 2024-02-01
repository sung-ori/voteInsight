package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
   
    //글 작성 뷰
    @GetMapping("/board/write") 
    public String boardWriteForm(){
        return "boardWrite";
    }

    //글작성
    @PostMapping("/board/writepro")
    public String boardwritePro(Board board){ 

      boardService.write(board);

        return "redirect:boardList";
    }


}
