package com.kisscotp.voteInsight.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.Vote;
import com.kisscotp.voteInsight.domain.dto.VoteDto;
import com.kisscotp.voteInsight.repository.VoteRepository;

@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepo;


    public boolean vote(Users voteUser,VoteDto dto) {
        // 투표 여부
        boolean isVote = false;
        // 투표 성공 여부
        boolean result = false;

        // 현재 선거의 표 모아옴.
        List<Vote> votes = voteRepo.findByElectionidx(dto.getElectionidx());
        // 확인 !
        for (Vote vote : votes) {
            if (vote.getUseridx().equals(voteUser.getUseridx())) {
                // 이 선거에 투표한 전적이 있으면 true
                isVote = true;
                break;
            }
        }

        //투표한 적 없다면
        if (!isVote) {
            dto.setUseridx(voteUser.getUseridx());
            dto.setVotetime(LocalDateTime.now());
            Vote vote = Vote.toEntity(dto);

            voteRepo.save(vote);
            result = true;
            return result;
        }

        return result;
    }
}

