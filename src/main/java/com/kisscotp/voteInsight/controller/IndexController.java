package com.kisscotp.voteInsight.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.dto.CandidateDto;
import com.kisscotp.voteInsight.service.BoardService;
import com.kisscotp.voteInsight.service.CandidateService;
import com.kisscotp.voteInsight.service.ElectionService;
import com.kisscotp.voteInsight.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    BoardService boardService;
    
     @Autowired
    private ElectionService electionService;

    @Autowired
    UserService userService;
    
    @Autowired
    CandidateService candiService;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal UserDetails user, Model m) {
                if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            m.addAttribute("user", loginUser);
        }

        return "/indexUser";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "/loginForm";
    }

    @GetMapping("signUp")
    public String signUp(@AuthenticationPrincipal UserDetails user, Model m){
        Users loginUser = userService.getUser(user.getUsername());
        m.addAttribute("user", loginUser);
        return "/admin/signUpForm";
    }

// 후보 등록 폼
    @GetMapping("/candidate/registForm")
    public String candidateForm (@AuthenticationPrincipal UserDetails user,
                                @RequestParam(name = "electionidx", defaultValue = "0") Long electionidx,
                                Model model) {
        Users loginUser = userService.getUser(user.getUsername());
        model.addAttribute("user", loginUser);
        // Long idx = electionidx;
        
        List<CandidateDto> dtoList = candiService.getCandidates(electionidx);
        Election election = candiService.getElection(electionidx);

        List<Users> userList = candiService.getCandidatesUsers(dtoList);

        log.debug("선거 인덱스 값을 알려주세요." + electionidx );
        model.addAttribute("candiDto", dtoList);
        model.addAttribute("candiUser", userList);
        model.addAttribute("election", election);


        

        return "/candidate/registForm";
    }
}
