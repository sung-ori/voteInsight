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
    public String main(@AuthenticationPrincipal UserDetails user, Model m) {
                if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            m.addAttribute("user", loginUser);
        }

        return "/indexUser";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "/loginForm";
    }

    //공지사항 목록
    @GetMapping("/board/list")
    public String boardlist(@AuthenticationPrincipal UserDetails user,Model model) {
        // 로그인 했으면 유저를 구분해줘야하고
        // 네브바에 표시 내용이 달라지므로 
        // 로그인 후 보는 페이지들에는  
        // @AuthenticationPrincipal UserDetails user 로
        // 현재 로그인중인 유저의 정보를 모델에 담아 보내줘야 함다 !
        // 사실 이건 사용자 이름 표시 안해줄 거면 할 필요 없긴 합니다,,,
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }
        model.addAttribute("boards", boardService.boardlist());
        return "/board/boardList";
    }

    //공지사항 상세조회
    @GetMapping("/board/view") 
    public String boardView(@AuthenticationPrincipal UserDetails user, 
                            @RequestParam(name="boardidx", defaultValue="0") Long idx,
                             Model model) {
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }
        Board board = boardService.boardview(idx);
        model.addAttribute("board", board);
        return "/board/boardView";
}

}