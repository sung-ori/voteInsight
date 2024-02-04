package com.kisscotp.voteInsight.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {


         private final BoardRepository boardRepository;

         //글 목록
         public List<Board> boardlist(){
            return boardRepository.findAll();
        }

         //글 상세       
        public Board boardview(Long idx){
            return boardRepository.findById(idx).get();
        }

         //글 삭제
         public void boardDelete(Long idx){
            
              boardRepository.deleteById(idx);
        }

        //글작성
    //     public void write(Board board){
    //        boardRepository.save(board);
    //    }
       
        
         //글 수정
        // public Long update(Long idx, Board board) {

        //     Board board = boardRepository.findById(idx);
        //     board.update(board.getTitle(), board.getContent());
        //     boardRepository.save(board);

        //     redirectAttributes.addAttribute("boardidx", board.getId());

        //     return "redirect:/board/view";

        // }

}
