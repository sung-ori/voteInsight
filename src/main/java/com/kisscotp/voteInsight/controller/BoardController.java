package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //공지사항 목록
  @GetMapping("/list")
public String boardlist(@AuthenticationPrincipal UserDetails user,
                        @RequestParam(name = "page", defaultValue = "0", required = true) int page,
                        Model model) {
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

    Page<Board> board = boardService.boardList(page);
    model.addAttribute("boards", board);
    
    return "/board/boardList";
}

    //공지사항 상세조회
    @GetMapping("/view") 
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

    //글 수정 뷰
     @GetMapping("/updateView/{boardidx}") 
     public String boardWrite(@AuthenticationPrincipal UserDetails user, 
                                 Model model, 
                            @PathVariable(name="boardidx") Long idx,
                                 BoardRequestDto requestDto){
         if(user != null) {
             Users loginUser = userService.getUser(user.getUsername());
             model.addAttribute("user", loginUser);
         }
         Board board = boardService.boardview(idx);

         model.addAttribute("requestDto",requestDto);
         model.addAttribute("board", board);

         return "/board/boardUpdate";
     }


     // 글 수정
     @PostMapping("/update/{boardidx}") 
     public String boardUpdate(@PathVariable Long boardidx, 
                           @ModelAttribute BoardRequestDto requestDto) { 
    boardService.boardUpdate(boardidx, requestDto); 
                               
   return "redirect:/board/list"; 
 }


   
}





                            
  
   

