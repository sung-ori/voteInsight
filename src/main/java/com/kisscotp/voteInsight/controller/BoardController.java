package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.service.BoardService;
import com.kisscotp.voteInsight.service.UserService;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;
    

    //글삭제
    @GetMapping("/board/delete/{boardidx}")
    public String boardDelete(@AuthenticationPrincipal UserDetails user, 
                                @RequestParam(name="boardidx", defaultValue="0") Long idx, 
                                Model model) {

        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }  
        boardService.boardDelete(idx);

        return "redirect:/board/boardlist";
    }

    //    //글작성
    //    @PostMapping("/board/writepro")
    //    public String boardwritePro(Board board){ 
   
    //      boardService.write(board);
   
    //        return "redirect:boardList";
    //    }

    //        //글 작성 뷰
    // @GetMapping("/board/write") 
    // public String boardWriteForm(@AuthenticationPrincipal UserDetails user, Model model){
    //     if(user != null) {
    //         Users loginUser = userService.getUser(user.getUsername());
    //         model.addAttribute("user", loginUser);
    //     }
    //     return "/board/boardWrite";
    // }


    //글 수정 뷰 
    // @GetMapping("/board/modify/{idx}")
    // public String boardmodify(@PathVariable("idx") Long idx,Model model){
    //     model.addAttribute("board", boardService.boardview(idx));
    //     return "boardmodify";
    // }

    // // 게시글 수정
    // @PutMapping("/board/update/{boardidx}")
    // public Long update(@PathVariable Long idx, @RequestBody Board board) {
    //     return boardService.update(idx, board);
    // }



}
