package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.service.UserService;

import groovy.util.logging.Slf4j;

@Controller
@Slf4j
@RequestMapping("user")
public class UserController {
    
    @Autowired
    UserService service;

    @PostMapping("/signUp")
    public String join(String year, Long grade,GroupType group, String name, String phone) {
        service.save(year, grade, group, name, phone);
        
        // 일단 홈으로 !
        return "redirect:/";
    }

    
}
