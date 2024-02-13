package com.kisscotp.voteInsight.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.service.BoardService;
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


}
