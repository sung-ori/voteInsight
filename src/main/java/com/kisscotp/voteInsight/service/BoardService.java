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

 

         
}
