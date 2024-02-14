package com.kisscotp.voteInsight.domain;

import com.kisscotp.voteInsight.domain.dto.CandidateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vi_candidate")
@SequenceGenerator(
    name = "candi_seq",             // 임의로 정하는 jpa에서 부를 이름
    sequenceName = "VI_CANDIDATE_SEQ",  // 실제 DB의 사용할 시퀀스 이름
    initialValue = 1,               // 초기값
    allocationSize = 1              // 한번에 할당받을 값의 갯수
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candi_seq")
    private Long    candidateidx;   // 후보 인덱스

    @Column
    private Long    useridx;        // 후보의 유저 인덱스
    
    @Column
    private Long    electionidx;    // 참가하는 선거의 인덱스
    
    @Column
    private Long    candinumber;    // 기호 몇 번

    @Column
    private String  imgpath;        // 홍보 이미지 경로

    @Transient // 데이터베이스에 매핑X
    private String  candidatename;  // 후보자의 이름

    @Transient // 데이터베이스에 매핑X
    private Long    voteCount;      // 후보자의 투표 수

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public static Candidate toEntity(CandidateDto dto) {
        return Candidate.builder()
                .candidateidx(dto.getCandidateidx())
                .useridx(dto.getUseridx())
                .electionidx(dto.getElectionidx())
                .candinumber(dto.getCandinumber())
                .imgpath(dto.getImgpath())
                .build();
    }
}
