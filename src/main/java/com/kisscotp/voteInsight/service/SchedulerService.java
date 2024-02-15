package com.kisscotp.voteInsight.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.repository.ElectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final LocalDate now = LocalDate.now();
    private final ElectionRepository electionRepository;

    // 진행 상황 0 = 준비, 1 = 진행중, 2 = 투표 종료 열람 가능, 3 = 열람기간 만료
    
    @Scheduled(cron = "0 0 0 * * ?")
    public void autoProgress() {
        List<Election> allElections = electionRepository.findAll();

        for (Election election : allElections) {
            switch (election.getProgress()) {
                case '0' : {
                    if (election.getStartdate().isBefore(now) || election.getStartdate().isEqual(now))
                        election.setProgress('1');
                    break;
                }
                case '1' : {
                    if (election.getDaeline().isBefore(now) || election.getDaeline().isEqual(now))
                        election.setProgress('2');
                    break;
                }
                case '2' : {
                    if (election.getEnddate().isBefore(now) || election.getEnddate().isEqual(now))
                        election.setProgress('3');
                    break;
                }
                
            }

            electionRepository.save(election);
        }
    }
}
