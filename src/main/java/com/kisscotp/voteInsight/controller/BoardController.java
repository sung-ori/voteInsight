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


    //페이징 
    // @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
    // @GetMapping("/board/paging")
    // public String paging(@PageableDefault(page = 1) Pageable pageable, @Login SessionUser user, Model model) {
    //     Page<BoardResponseDto> boardPages = boardService.paging(pageable); 
    //     /**
    //      * blockLimit : page 개수 설정
    //      * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
    //      * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
    //      */
    //     int blockLimit = 10;
    //     int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
    //     int endPage = Math.min((startPage + blockLimit - 1), boardPages.getTotalPages());
 
    //     model.addAttribute("boardPages", boardPages);
    //     model.addAttribute("startPage", startPage);
    //     model.addAttribute("endPage", endPage);
    //     return "paging";
    // }
}





                            
  
   

