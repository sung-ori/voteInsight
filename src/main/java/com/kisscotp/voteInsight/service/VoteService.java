package com.kisscotp.voteInsight.service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Candidate;
import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.Vote;
import com.kisscotp.voteInsight.domain.dto.VoteDto;
import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.repository.CandidateRepository;
import com.kisscotp.voteInsight.repository.ElectionRepository;
import com.kisscotp.voteInsight.repository.UsersRepository;
import com.kisscotp.voteInsight.repository.VoteRepository;

@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepo;

    @Autowired
    private UserService userService; 

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final UsersRepository usersRepository;
    private final ElectionRepository electionRepository;




     @Autowired
    public VoteService(VoteRepository voteRepository, CandidateRepository candidateRepository,UsersRepository usersRepository,ElectionRepository electionRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.usersRepository = usersRepository;
        this.electionRepository =electionRepository;

    }


    //선거 결과 조회 

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




    //학과투표현황퍼센티지
     public double votePercentage(Long idx) {
        // 현재 선거에 해당하는 학과
        Election election = electionRepository.findById(idx)
                             .orElseThrow(() -> new NoSuchElementException("선거를 찾을 수 없습니다."));
        GroupType electionGroupType = election.getGrouptype();

        // 해당 학과의 총 사람 수
        double countGroup = userService.countGroup(electionGroupType);

        //현재 선거의 투표 수
        int  countVote = voteRepo.countByElectionidx(idx);
        
        // 퍼센테이지 계산
        if (countVote != 0) {
            return (double) countVote / countGroup * 100;
        } else {
            return 0.0;
        }
    }

}

