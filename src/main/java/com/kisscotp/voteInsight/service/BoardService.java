package com.kisscotp.voteInsight.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.domain.BoardResponseDto;
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

      // 게시글 저장 
      public BoardResponseDto boardSave(Board board) {
        
         return  boardRepository.save(board); 
        }

}
