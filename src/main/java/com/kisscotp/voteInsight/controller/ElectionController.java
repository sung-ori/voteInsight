package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.service.ElectionService;
import com.kisscotp.voteInsight.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/election")
@Slf4j
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @Autowired
    private UserService userService;

  
    //공지사항 상세조회
    @GetMapping("/view") 
    public String boardView(@AuthenticationPrincipal UserDetails user, 
                            @RequestParam(name="electionidx", defaultValue="0") Long idx,
                             Model model) {
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }

        Election election = electionService.electionview(idx);

        model.addAttribute("election", election);
        return "/election/electionView";
}

    
   
}


   

