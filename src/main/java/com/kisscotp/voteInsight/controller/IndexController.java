package com.kisscotp.voteInsight.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.service.BoardService;
import com.kisscotp.voteInsight.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;
    

    @GetMapping("/")
    public String main() {
        

        return "/indexUser";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "/loginForm";
    }

    //공지사항 목록
    @GetMapping("/board/list")
    public String boardlist(Model model) {
        model.addAttribute("boards", boardService.boardlist());
        return "/boardList";
    }

    //공지사항 상세조회
    @GetMapping("/board/view") 
    public String boardView(@RequestParam(name="boardidx", defaultValue="0") Long idx, Model model) {
                Board board = boardService.boardview(idx);
               model.addAttribute("board", board);
        return "/boardView";
}
//navbar
    @GetMapping("/navBar")
    public String navBar(@AuthenticationPrincipal UserDetails user, Model m) {
        // 일단 여기로 지정해 놓았음!
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            m.addAttribute("user", loginUser);
        }
        
        return "/fragments/navBar";
    }
    
    @GetMapping("/footer")
    public String footer() {
        
        return"/fragments/footer";
    }
}
