package com.kisscotp.voteInsight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.repository.ElectionRepository;

@Service
public class VoteService {

        private final ElectionRepository electionRepository;

        @Autowired
        public VoteService(ElectionRepository electionRepository) {
            this.electionRepository = electionRepository;
        }


    //     //결과조회>투표종료리스트만 조회
    //      public List<Election> getElectionsInProgress() {
    //      return electionRepository.findByProgress('2');
    // }



}
