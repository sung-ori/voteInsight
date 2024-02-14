package com.kisscotp.voteInsight.domain.dto;

import java.time.LocalDateTime;

import com.kisscotp.voteInsight.domain.Vote;

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
public class VoteDto {
    private Long            voteidx;        // 표 인덱스

    private Long            electionidx;    // 선거의 인덱스

    private Long            candidateidx;    // 후보의 인덱스

    private Long            useridx;        // 투표자의 인덱스

    private LocalDateTime   votetime;       // 투표한 시간

    private String          ip;             // 투표 장소의 ip주소

    public static VoteDto toDto(Vote entity) {
        return VoteDto.builder()
                .voteidx(entity.getVoteidx())
                .electionidx(entity.getElectionidx())
                .candidateidx(entity.getCandidateidx())
                .useridx(entity.getUseridx())
                .votetime(entity.getVotetime())
                .ip(entity.getIp())
                .build();
    }
}
