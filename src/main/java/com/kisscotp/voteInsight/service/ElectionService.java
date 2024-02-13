package com.kisscotp.voteInsight.service;

import java.time.LocalDate;
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
    String defaultPath;

    //선거 목록
    public List<Election> electionlist() {
        return electionRepository.findAll();
    }

    //선거 상세
    public Election electionview(Long idx) {
        return electionRepository.findById(idx).get();
    }

    //선거 삭제
    public void electionDelete(Long idx) {
        electionRepository.deleteById(idx);
    }

    //선거 생성
    public void save(Election election, MultipartFile uploadFile) {
    LocalDateTime now1 = LocalDateTime.now();
    
    String saveFileName = fileService.saveFile(uploadFile, defaultPath+"/posters");
    election.setPosterpath(saveFileName);
    
    election.setCreatedate(now1);

    LocalDate now = LocalDate.now();
    LocalDate startdate = election.getStartdate();
    LocalDate daeline = election.getDaeline();
    LocalDate enddate = election.getEnddate();

    if (startdate != null && now.isBefore(startdate)) {
        election.setProgress('0'); // 준비중
    } else if (startdate != null && (now.isEqual(startdate) || (now.isAfter(startdate) && now.isBefore(daeline)))) {
        election.setProgress('1'); // 진행중
    } else if (daeline != null && now.isAfter(daeline) && now.isBefore(enddate)) {
        election.setProgress('2'); // 투표 종료 열람 가능
    } else {
        election.setProgress('3'); // 열람기간 만료
    }

    
    
    electionRepository.save(election);
}



      // 선거 수정 
      public void electionUpdate(Long idx, ElectionRequestDto requestDto,MultipartFile uploadFile) {
        Election election = electionRepository.findById(idx)
                .orElseThrow(() -> new RuntimeException("Election not found"));
   
        String saveFileName = fileService.saveFile(uploadFile, defaultPath+"/posters");
        election.setPosterpath(saveFileName);
        election.update(requestDto.getTitle(),requestDto.getGrouptype(),requestDto.getCreatedate(),requestDto.getStartdate(),requestDto.getDaeline(),requestDto.getEnddate());

   // 수정 시간을 기준으로 투표 상태 다시 계산하여 업데이트
       LocalDate now = LocalDate.now();
       LocalDate startdate = election.getStartdate();
       LocalDate daeline = election.getDaeline();
       LocalDate enddate = election.getEnddate();
   
       if (startdate != null && now.isBefore(startdate)) {
        election.setProgress('0'); // 준비중
    } else if (startdate != null && (now.isEqual(startdate) || (now.isAfter(startdate) && now.isBefore(daeline)))) {
        election.setProgress('1'); // 진행중
    } else if (daeline != null && now.isAfter(daeline) && now.isBefore(enddate)) {
        election.setProgress('2'); // 투표 종료 열람 가능
    } else {
        election.setProgress('3'); // 열람기간 만료
    }
        electionRepository.save(election);
   }

     //결과 조회용 선거 목록
     public List<Election> resultElectionlist() {
        return electionRepository.findByProgress('2');
    }

}