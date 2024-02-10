package com.kisscotp.voteInsight.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.repository.ElectionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ElectionService {

     private final ElectionRepository electionRepository;

       //선거 목록
         public List<Election> electionlist(){
            return electionRepository.findAll();
        }

        //선거 상세       
        public Election electionview(Long idx){
            return electionRepository.findById(idx).get();
        }

 }
        
    
