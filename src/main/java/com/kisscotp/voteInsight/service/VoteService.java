package com.kisscotp.voteInsight.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Candidate;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.Vote;
import com.kisscotp.voteInsight.repository.CandidateRepository;
import com.kisscotp.voteInsight.repository.UsersRepository;
import com.kisscotp.voteInsight.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final UsersRepository usersRepository;



     @Autowired
    public VoteService(VoteRepository voteRepository, CandidateRepository candidateRepository,UsersRepository usersRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.usersRepository = usersRepository;

    }


    //선거 결과 조회 

    // public List<Candidate> getCandidates(Long electionIdx) {
    //    //후보자 이름 가져오기 
    //     List<Candidate> candidates = candidateRepository.findByElectionidx(electionIdx);
        
      
    //     for (Candidate candidate : candidates) {
    //         Long useridx = candidate.getUseridx();
    //         Optional<Users> userOptional = usersRepository.findById(useridx);
            
    //         if (userOptional.isPresent()) {
    //             Users user = userOptional.get();
    //             String candidatename = user.getName();
    //             candidate.setCandidatename(candidatename);
    //         } else {  }          
    //     }    
        
    //     return candidates;
    // }

    public List<Candidate> getCandidates(Long electionIdx) {
    // 선거에 등록된 후보자 목록 조회
    List<Candidate> candidates = candidateRepository.findByElectionidx(electionIdx);

    // 후보자의 투표 수를 담을 Map 초기화
    Map<Long, Long> candidateVoteCounts = new HashMap<Long,Long>();
    for (Candidate candidate : candidates) {
        candidateVoteCounts.put(candidate.getCandidateidx(), 0L); // 각 후보자의 투표 수를 0으로 초기화
    }

    // 투표 테이블에서 각 후보자의 투표 수 계산
    List<Vote> votes = voteRepository.findByElectionidx(electionIdx);
    
    for (Vote vote : votes) {
        Long candidateidx = vote.getCandidateidx();
        candidateVoteCounts.put(candidateidx, candidateVoteCounts.getOrDefault(candidateidx, 0L) + 1); // 해당 후보자의 투표 수 증가
    }

    // 후보자의 득표 수를 후보자 객체에 추가하고 후보자 이름 가져오기
    for (Candidate candidate : candidates) {
        Long useridx = candidate.getUseridx();
       
        Optional<Users> userOptional = usersRepository.findById(useridx);
       
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            String candidatename = user.getName();
            candidate.setCandidatename(candidatename);
        }

        Long candidateIdx = candidate.getCandidateidx();
        Long voteCount = candidateVoteCounts.getOrDefault(candidateIdx, 0L);
        candidate.setVoteCount(voteCount);
    }

    return candidates;
}

  
    

}

