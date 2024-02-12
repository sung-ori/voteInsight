package com.kisscotp.voteInsight.domain;

import java.time.LocalDate;

import com.kisscotp.voteInsight.domain.enums.GroupType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vi_election")
@SequenceGenerator(
    name = "election_seq",              // 임의로 정하는 jpa에서 부를 이름
    sequenceName = "VI_ELECTION_SEQ",   // 실제 DB의 사용할 시퀀스 이름
    initialValue = 1,                   // 초기값
    allocationSize = 1                  // 한번에 할당받을 값의 갯수
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Election {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "election_seq")
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
    private LocalDate   createdate;     // 선거 생성일

    @Column
    private LocalDate   startdate;      // 투표 시작일

    @Column
    private LocalDate   daeline;        // 투표 종료일

    @Column
    private LocalDate   enddate;        // 열람 마감일


      // ElectionRequestDto의 내용을 이용해 Election 객체를 생성하는 생성자
      public Election(ElectionRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.grouptype = requestDto.getGrouptype();
        this.posterpath = requestDto.getPosterpath();
        this.progress = requestDto.getProgress();
        this.createdate = LocalDateTime.now(); // 생성일은 현재 날짜로 설정
        this.startdate = requestDto.getStartdate();
        this.daeline = requestDto.getDaeline();
        this.enddate = requestDto.getEnddate();
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
