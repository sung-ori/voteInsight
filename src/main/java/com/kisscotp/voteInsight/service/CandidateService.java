package com.kisscotp.voteInsight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kisscotp.voteInsight.domain.Candidate;
import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.dto.CandidateDto;
import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.repository.CandidateRepository;
import com.kisscotp.voteInsight.repository.ElectionRepository;
import com.kisscotp.voteInsight.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateService {
    
    @Autowired
    CandidateRepository candiRepo;
    @Autowired
    UsersRepository userRepo;
    @Autowired
    ElectionRepository electionRepo;
    @Value("${spring.servlet.multipart.location}")
    String defaultPath;

    private final FileService fileService;

    public List<CandidateDto> getCandidates(Long electionidx) {
        List<CandidateDto> dtoList = new ArrayList<>();
        Sort sort = Sort.by(Sort.Direction.ASC,"candinumber");
        List<Candidate> entityList =  candiRepo.findByElectionidx(electionidx,sort);

        if(entityList != null) {
            for (Candidate candidate : entityList) {
                dtoList.add(CandidateDto.toDto(candidate));
            }
        }
        
        return dtoList;
    }

    // 후보자의 Users객체 정보를 가져온다.
    public List<Users> getCandidatesUsers(List<CandidateDto> dtoList) {
        List<Users> userList = new ArrayList<>();;
        
        if(dtoList != null) {
            for (CandidateDto dto : dtoList) {
                userList.add(userRepo.findById(dto.getUseridx()).get());
            }
        }
        

        return userList;
    }

    // 검색 시 like검색으로 찾아온다. 선거에 해당하는 그룹만.
    public List<Users> findByStudentidContanting(String studentid,Long electionidx) {
        List<Users> userList = new ArrayList<>();

        GroupType grouptype  = electionRepo.findById(electionidx).get().getGrouptype();

        List<Users> list = userRepo.findByStudentidContaining(studentid);

        for(Users user : list) {
            if (user.getGrouptype() == grouptype) {
                userList.add(user);
            }
        }
        return userList;
    }

    public Users selectUser(Long useridx) {
        return userRepo.findById(useridx).get();
    }

    public Election getElection(Long electionidx) {
        return electionRepo.findById(electionidx).get();
    }

    // 후보에 등록한다.
    public void regist(CandidateDto dto, MultipartFile uploadFile) {
        Long candinumber;
        String fileName = fileService.saveFile(uploadFile,defaultPath+"/poster");
        dto.setImgpath(fileName);

        Sort sort = Sort.by(Sort.Direction.DESC,"candinumber");

        List<Candidate> candiList = candiRepo.findByElectionidx(dto.getElectionidx(),sort);
        
        if(candiList.size() <= 0) {
            candinumber = 1L;
        }
        else {
            candinumber = candiList.get(0).getCandinumber() + 1L;
        }
        dto.setCandinumber(candinumber);

        Candidate candidate = Candidate.toEntity(dto);

        candiRepo.save(candidate);
        
    }

    public boolean doubleCheck(Long useridx, Long electionidx) {
        boolean result = false;

        List<Candidate> list = candiRepo.findByElectionidx(electionidx);

        for (Candidate candi : list) {
            System.out.println(candi.toString());
            if (candi.getUseridx().equals(useridx)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
