package com.kisscotp.voteInsight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kisscotp.voteInsight.domain.Election;
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

        public void save(Election election, MultipartFile uploadFile) {
          String saveFileName = fileService.saveFile(uploadFile, defualtPath+"/posters");
          election.setPosterpath(saveFileName);
          electionRepository.save(election);
        }
 }
        
    
