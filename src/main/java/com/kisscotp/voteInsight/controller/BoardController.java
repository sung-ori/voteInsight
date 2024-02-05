package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kisscotp.voteInsight.domain.Board;
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

   

    // 글 수정 뷰 
    // @GetMapping("/modify/{boardidx}")
    // public String boardModify(@PathVariable("idx") Long idx, Model model){
    //     model.addAttribute("board", boardService.boardview(idx));
    //     return "boardmodify";
    // }

    // 게시글 수정
    // @PutMapping("/update/{boardidx}")
    // public Long update(@PathVariable Long idx, @RequestBody Board board) {
    //     return boardService.update(idx, board);
    // }
}
