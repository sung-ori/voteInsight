package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    UserService service;

    @PostMapping("join")
    public String join(Users user) {

        service.save(user);
        // 일단 홈으로 !
        return "/";
    }

    
}
