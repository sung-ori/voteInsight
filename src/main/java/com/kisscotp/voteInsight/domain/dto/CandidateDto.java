package com.kisscotp.voteInsight.domain.dto;

import com.kisscotp.voteInsight.domain.Candidate;

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

public class CandidateDto {
    private Long    candidateidx;   // 후보 인덱스

    private Long    useridx;        // 후보의 유저 인덱스
    
    private Long    electionidx;    // 참가하는 선거의 인덱스
    
    private Long    candinumber;    // 기호 몇 번

    private String  imgpath;        // 홍보 이미지 경로

    public static CandidateDto toDto(Candidate entity) {
        return CandidateDto.builder()
                .candidateidx(entity.getCandidateidx())
                .useridx(entity.getUseridx())
                .electionidx(entity.getElectionidx())
                .candinumber(entity.getCandinumber())
                .imgpath(entity.getImgpath())
                .build();
    }
}
