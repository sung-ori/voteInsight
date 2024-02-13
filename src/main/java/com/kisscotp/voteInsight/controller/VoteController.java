package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kisscotp.voteInsight.domain.Users;
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

    //선거결과>선거 목록
   @GetMapping("result")
   public String electionlist(@AuthenticationPrincipal UserDetails user,Model model) {
      
       if(user != null) {
           Users loginUser = userService.getUser(user.getUsername());
           model.addAttribute("user", loginUser);
       }
      
       model.addAttribute("elections", electionService.electionlist());
       
       return "/vote/resultList";
   }

   

}
