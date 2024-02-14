package com.kisscotp.voteInsight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.dto.CandidateDto;
import com.kisscotp.voteInsight.domain.dto.VoteDto;
import com.kisscotp.voteInsight.service.CandidateService;
import com.kisscotp.voteInsight.service.ElectionService;
import com.kisscotp.voteInsight.service.UserService;
import com.kisscotp.voteInsight.service.VoteService;



@Controller
@lombok.extern.slf4j.Slf4j
@RequestMapping("vote")
public class VoteController {

    @Autowired
    private VoteService service;
     
    @Autowired
    private ElectionService electionService;

    @Autowired
    private UserService userService;

    @Autowired
    CandidateService candiService;

    //선거결과>선거 목록
   @GetMapping("result")
   public String resultList(@AuthenticationPrincipal UserDetails user,Model model) {
      
       if(user != null) {
           Users loginUser = userService.getUser(user.getUsername());
           model.addAttribute("user", loginUser);
       }
      
       model.addAttribute("elections", electionService.resultElectionlist());
       
       return "/vote/resultList";
   }

   //선겨결과 조회
   

    @GetMapping("")
    public String voteForm(@AuthenticationPrincipal UserDetails user, Long electionidx, Model model) {
    
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }
        Election election = candiService.getElection(electionidx);
        model.addAttribute("election", election);
        List<CandidateDto> dtoList = candiService.getCandidates(electionidx);
        model.addAttribute("candidates", dtoList);
        List<Users> candiUserList = candiService.getCandidatesUsers(dtoList);
        model.addAttribute("candiUser", candiUserList);

        return "/vote/voteForm";
    }

    @PostMapping("/vote")
    @ResponseBody
    public boolean vote(@AuthenticationPrincipal UserDetails user, VoteDto dto) {
        Users voteUser = userService.getUser(user.getUsername());
        boolean result = service.vote(voteUser,dto);
        return result;
    }


}
