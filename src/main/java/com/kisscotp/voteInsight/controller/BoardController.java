package com.kisscotp.voteInsight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
   
    //글 작성 뷰
    @GetMapping("/board/write") 
    public String boardWriteForm(){
        return "boardwrite";
    }
   


}
