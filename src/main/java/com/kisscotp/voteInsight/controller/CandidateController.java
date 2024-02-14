package com.kisscotp.voteInsight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.dto.CandidateDto;
import com.kisscotp.voteInsight.service.CandidateService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("candidate")
@Slf4j
public class CandidateController {
    @Autowired
    CandidateService service;

    
    

    @GetMapping("/searchForm")
    public String searchForm(Long electionidx, Model model) {
        Election election = service.getElection(electionidx);
        model.addAttribute("election", election);
        return"/candidate/userSearchForm";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Users> search(@RequestParam(name = "studentid", defaultValue = " ") String studentid,
                        Long electionidx,
                        Model model) {
        List<Users> searchUsers; 
        
        searchUsers = service.findByStudentidContanting(studentid,electionidx);
        if (searchUsers != null) {
            // model.addAttribute("users", searchUsers);
        }
        return searchUsers;
    }

    @GetMapping("/selectUser")
    @ResponseBody
    public Users select(@RequestParam(name = "useridx", defaultValue = "0") Long useridx) {
        return service.selectUser(useridx);
    }

    @PostMapping("/regist")
    public String regist(CandidateDto dto, MultipartFile uploadFile) {

        service.regist(dto, uploadFile);

        return"redirect:/candidate/registForm?electionidx="+dto.getElectionidx(); 
    }
    
    @GetMapping("/doubleCheck")
    @ResponseBody
    public boolean doubleCheck(Long useridx, Long electionidx) {
        log.debug("유저 : " + useridx + "선거 : " + electionidx);
        boolean result = service.doubleCheck(useridx, electionidx);
        log.debug("참 거짓 알려줘 : " + result);
        return result;
    }
}

