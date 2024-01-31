package com.kisscotp.voteInsight.domain;

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
@Table(name = "vi_candidate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    candidateidx;   // 후보 인덱스

    @Column
    private Long    useridx;        // 후보의 유저 인덱스
    
    @Column
    private Long    electionidx;    // 참가하는 선거의 인덱스
    
    @Column
    private Long    candinumber;    // 기호 몇 번

    @Column
    private String  imgpath;        // 홍보 이미지 경로
}
