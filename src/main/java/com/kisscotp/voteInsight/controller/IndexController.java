package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kisscotp.voteInsight.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    private final BoardService boardService;

    @Autowired
    public IndexController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String main() {
        // 일단 여기로 지정해 놓았음!
        return "/indexUser";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "/loginForm";
    }

    //공지사항 조회
    @GetMapping("/board/list")
    public String boardlist(Model model) {
        model.addAttribute("boards", boardService.boardlist());
        return "boardList";
    }

}
