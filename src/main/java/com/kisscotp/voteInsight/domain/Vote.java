package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
