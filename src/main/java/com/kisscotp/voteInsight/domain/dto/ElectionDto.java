package com.kisscotp.voteInsight.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kisscotp.voteInsight.domain.enums.GroupType;

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
public class ElectionDto {
    private Long            electionidx;    // 인덱스

    private String          title;          // 선거 제목

    private GroupType       grouptype;      // 투표 대상 그룹

    private String          posterpath;     // 포스터 경로

    private char            progress;       // 진행 상황 0 = 준비, 1 = 진행중, 2 = 투표 종료 열람 가능, 3 = 열람기간 만료

    private LocalDateTime   createdate;     // 선거 생성일

    private LocalDate   startdate;      // 투표 시작일

    private LocalDate   daeline;        // 투표 종료일

    private LocalDate   enddate;        // 열람 마감일

    
}
