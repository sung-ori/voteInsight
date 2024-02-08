package com.kisscotp.voteInsight.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.domain.BoardRequestDto;
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
        
        // 글 작성 
         public BoardResponseDto boardSave(Board board) {
             Board savedBoard = boardRepository.save(board);
        
            return new BoardResponseDto(savedBoard); 
        
         }

        // 글 수정    
        public void boardUpdate(Long idx, BoardRequestDto requestDto) {
            Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new RuntimeException("Board not found"));

            board.update(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());

            boardRepository.save(board);
        }

    // 게시글 상세 조회(수정용)
    public BoardResponseDto findBoardById(Long idx) {
        Optional<Board> optionalBoard = boardRepository.findById(idx);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return new BoardResponseDto(board);
        } else {
            throw new RuntimeException("게시물을 찾을 수 없습니다: " + idx);
        }
    }

}
        
    
