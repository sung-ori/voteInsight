package com.kisscotp.voteInsight.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.ElectionRequestDto;
import com.kisscotp.voteInsight.repository.ElectionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ElectionService {

     private final ElectionRepository electionRepository;
    
     private final FileService fileService;

     @Value("${spring.servlet.multipart.location}")
     String defualtPath;

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

        //선거 생성
        public void save(Election election, MultipartFile uploadFile) {
          String saveFileName = fileService.saveFile(uploadFile, defualtPath+"/posters");
          election.setPosterpath(saveFileName);
          electionRepository.save(election);
        }

         // 선거 수정 
         public void electionUpdate(Long idx, ElectionRequestDto requestDto) {
            Election election = electionRepository.findById(idx)
                .orElseThrow(() -> new RuntimeException("Board not found"));

            // 선거 정보 업데이트
            election.setTitle(requestDto.getTitle());
            election.setGrouptype(requestDto.getGrouptype());
            election.setPosterpath(requestDto.getPosterpath());
            election.setStartdate(requestDto.getStartdate());
            election.setDaeline(requestDto.getDaeline());
            election.setEnddate(requestDto.getEnddate());
          
            // 선거의 진행 상태를 계산하여 업데이트
             LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(election.getStartdate())) {
                  election.setProgress('0'); // 준비중
            } else if (now.isAfter(election.getDeadline())) {
                  election.setProgress('2'); // 투표 종료 열람 가능
            } else {
                election.setProgress('1'); // 진행중
        }

        electionRepository.save(election);
    }

 }
        
    
