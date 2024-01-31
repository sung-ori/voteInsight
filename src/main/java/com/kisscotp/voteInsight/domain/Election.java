package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import com.kisscotp.voteInsight.domain.enums.GroupType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vi_election")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Election {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            electionidx;    // 인덱스

    @Column
    private String          title;          // 선거 제목

    @Enumerated(EnumType.STRING)
    @Column
    private GroupType       grouptype;      // 투표 대상 그룹

    private String          posterpath;     // 포스터 경로

    @Column
    private char            progress;       // 진행 상황 0 = 준비, 1 = 진행중, 2 = 투표 종료 열람 가능, 3 = 열람기간 만료

    @Column
    private LocalDateTime   createdate;     // 선거 생성일

    @Column
    private LocalDateTime   startdate;      // 투표 시작일

    @Column
    private LocalDateTime   daeline;        // 투표 종료일

    @Column
    private LocalDateTime   enddate;        // 열람 마감일
}
