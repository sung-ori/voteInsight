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


        //글작성
        public void write(Board board){
            boardRepository.save(board);
        }
 

         
}
