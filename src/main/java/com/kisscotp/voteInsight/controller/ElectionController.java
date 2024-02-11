package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kisscotp.voteInsight.domain.Election;
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

  
    @PostMapping("/create")
    public String electionCreate(Election election, MultipartFile uploadFile) {
        electionService.save(election,uploadFile);

        return "redirect:/elction/list";
    }

        
    
   
}


   

