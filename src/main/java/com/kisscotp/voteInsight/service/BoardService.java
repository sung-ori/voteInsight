package com.kisscotp.voteInsight.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        //글 목록(페이징처리)
       
        public Page<Board> boardList(int page) {
            Sort sort = Sort.by(Sort.Direction.DESC, "createtime");
    
            Pageable pageable = PageRequest.of(page, 10, sort);
    
            return boardRepository.findAll(pageable);
        }
    




         //글 상세       
        public Board boardview(Long idx){
            return boardRepository.findById(idx).get();
        }

         //글 삭제
         public void boardDelete(Long idx){
            
              boardRepository.deleteById(idx);
        }
        
        // 글 저장 
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

        // //페이징
        // public Page<BoardResponseDto> paging(Pageable pageable) {
        //     int page = pageable.getPageNumber(); // 페이지 번호는 0부터 시작
        //     int pageLimit = 10; // 한 페이지에 보여줄 글 개수
            
        //     // 한 페이지당 10개식 글을 보여주고 정렬 기준은 IDX 기준으로 내림차순
        //     Page<Board> boardPages = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "idx")));
        
        //     // 목록 : id, title, contents, username
        //     return boardPages.map(BoardResponseDto::new);

        // }


 }
        
    
