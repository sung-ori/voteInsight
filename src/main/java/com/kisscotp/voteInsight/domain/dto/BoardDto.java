package com.kisscotp.voteInsight.domain.dto;

import java.time.LocalDateTime;

import com.kisscotp.voteInsight.domain.Board;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Long                boardidx;           // 공지사항 인덱스

    private String              title;              // 제목

    private String              contents;           // 내용

    private String              username;           // 기세자 이름
    
    private LocalDateTime       createtime;         // 생성시간

    private LocalDateTime       updatetime;         // 수정 시간

    public static BoardDto toDto(Board entity) {
        return BoardDto.builder()
                .boardidx(entity.getBoardidx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .username(entity.getUsername())
                .createtime(entity.getCreatetime())
                .updatetime(entity.getUpdatetime())
                .build();
    }
}
