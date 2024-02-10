package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long boardidx; // PK
    private String title; // 제목
    private String contents; // 내용
    private String username; // 작성자
    private LocalDateTime createtime; // 생성일
    private LocalDateTime updatetime; // 수정일

    public BoardResponseDto(Board board) {
        this.boardidx = board.getBoardidx();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.username = board.getUsername();
        this.createtime = board.getCreatetime();
        this.updatetime = board.getUpdatetime();
    }

    public Long getBoardidx() {
        return this.boardidx;
    }
    


}
