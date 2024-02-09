package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import com.kisscotp.voteInsight.domain.enums.GroupType;

import lombok.Getter;

@Getter
public class ElectionResponseDto {

    private Long electionidx; // 선거 인덱스
    private String title; // 선거 제목
    private GroupType grouptype; // 투표 대상 그룹
    private String posterpath; // 포스터 경로
    private char progress; // 진행 상황
    private LocalDateTime createdate; // 선거 생성일
    private LocalDateTime startdate; // 투표 시작일
    private LocalDateTime daeline; // 투표 마감일
    private LocalDateTime enddate; // 열람 마감일

    public ElectionResponseDto(Election election) {
        this.electionidx = election.getElectionidx();
        this.title = election.getTitle();
        this.grouptype = election.getGrouptype();
        this.posterpath = election.getPosterpath();
        this.progress = election.getProgress();
        this.createdate = election.getCreatedate();
        this.startdate = election.getStartdate();
        this.daeline = election.getDaeline();
        this.enddate = election.getEnddate();
    }

    // 진행 상황 한글로 반환하는 메서드
    public String getProgressKorean() {
        switch (progress) {
            case '0':
                return "준비중";
            case '1':
                return "진행중";
            case '2':
                return "투표 종료 열람 가능";
            case '3':
                return "열람기간 만료";
            default:
                return "알 수 없음";
        }
    }
}
