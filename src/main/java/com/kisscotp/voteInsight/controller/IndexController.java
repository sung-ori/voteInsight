package com.kisscotp.voteInsight.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String main() {
        // 일단 여기로 지정해 놓았음!
        return "/indexUser";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "/loginForm";
    }

}
