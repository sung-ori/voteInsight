package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "vi_vote")
@SequenceGenerator(
    name = "vote_seq",             // 임의로 정하는 jpa에서 부를 이름
    sequenceName = "VI_VOTE_SEQ",  // 실제 DB의 사용할 시퀀스 이름
    initialValue = 1,               // 초기값
    allocationSize = 1              // 한번에 할당받을 값의 갯수
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
    private Long            voteidx;        // 표 인덱스

    @Column
    private Long            electionidx;    // 선거의 인덱스

    @Column
    private Long            cadidateidx;    // 후보의 인덱스

    @Column
    private Long            useridx;        // 투표자의 인덱스

    @Column
    private LocalDateTime   votetime;       // 투표한 시간

    @Column
    private String          ip;             // 투표 장소의 ip주소
}
