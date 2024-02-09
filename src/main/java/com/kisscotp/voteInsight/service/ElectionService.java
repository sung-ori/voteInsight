package com.kisscotp.voteInsight.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.ElectionResponseDto;
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
        
        //선거 삭제
        public void electionDelete(Long idx){
            
            electionRepository.deleteById(idx);
        }


        // 선거 저장 
        public ElectionResponseDto electionSave(Election election) {
            Election savedelection = electionRepository.save(election);
       
           return new ElectionResponseDto(savedelection); 
       
        }

 }
        
    
