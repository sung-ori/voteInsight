package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.service.BoardService;
import com.kisscotp.voteInsight.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    
    //글 작성 뷰
    @GetMapping("/board/write") 
    public String boardWriteForm(@AuthenticationPrincipal UserDetails user, Model model){
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }
        return "/board/boardWrite";
    }

    //글작성
    @PostMapping("/board/writepro")
    public String boardwritePro(Board board){ 

      boardService.write(board);

        return "redirect:boardList";
    }


}
