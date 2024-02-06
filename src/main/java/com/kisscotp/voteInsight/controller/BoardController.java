package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.domain.BoardRequestDto;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.service.BoardService;
import com.kisscotp.voteInsight.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    // 글 삭제
    @PostMapping("/delete/{boardidx}")
    public String boardDelete(@AuthenticationPrincipal UserDetails user,
                              @PathVariable Long boardidx,
                              Model model) {
        if (user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }

        boardService.boardDelete(boardidx);

        return "redirect:/board/list";
    }

    //글 작성 뷰
    @GetMapping("/write") 
    public String boardWrite(@AuthenticationPrincipal UserDetails user, 
                                Model model, 
                                BoardRequestDto requestDto){
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }
        model.addAttribute("requestDto",requestDto);

        return "/board/boardWrite";
    }

    //글 작성
    @PostMapping("/writepro")
    public String boardWritePro(@ModelAttribute("requestDto")  BoardRequestDto requestDto) {   
        
        Board board = new Board(requestDto);
       
        boardService.boardSave(board);
         
         return "redirect:/board/list";
        
    }
    

}
