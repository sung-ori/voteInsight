package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import com.kisscotp.voteInsight.domain.enums.GroupType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectionRequestDto {

    private String title;
    private GroupType grouptype;
    private String posterpath;
    private char progress;
    private LocalDateTime createdate;
    private LocalDateTime startdate;
    private LocalDateTime daeline;
    private LocalDateTime enddate;

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

    public Election toEntity() {
        return Election.builder()
                .title(title)
                .grouptype(grouptype)
                .posterpath(posterpath)
                .progress(progress)
                .createdate(createdate)
                .startdate(startdate)
                .daeline(daeline)
                .enddate(enddate)
                .build();
    }
}
