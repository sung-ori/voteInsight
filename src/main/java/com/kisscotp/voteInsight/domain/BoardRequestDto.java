package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//클라이언트로부터 전달된 게시글 작성에 필요한 정보담기
@Getter
@Setter
@Builder
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title; 
    private String contents; 
    private String username; 
    private LocalDateTime createtime; 


    public Board toEntity() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .username(username)
                .createtime(createtime)
                .build();
    }
}