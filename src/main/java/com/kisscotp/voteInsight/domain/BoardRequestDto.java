package com.kisscotp.voteInsight.domain;

import lombok.Getter;
import lombok.Setter;

//클라이언트로부터 전달된 게시글 작성에 필요한 정보담기
@Getter
@Setter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title; 
    private String contents; 
    private String username; 

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .username(username)
                .build();
    }
}