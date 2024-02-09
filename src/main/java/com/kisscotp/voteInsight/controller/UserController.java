package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.service.UserService;



@Controller
@lombok.extern.slf4j.Slf4j
@RequestMapping("user")
public class UserController {
    
    @Autowired
    UserService service;

    @PostMapping("/signUp")
    public String join(String year, Long grade,GroupType group, String name, String phone) {
        
        service.save(service.createUser(year, grade, group, name, phone));
        
        // 일단 홈으로 !
        return "redirect:/";
    }

    @GetMapping("/list")
    public String search(@AuthenticationPrincipal UserDetails user,
                        @RequestParam(name = "type", required = false) String type,
                        @RequestParam(name = "keyword", required = false)String keyword,
                        @RequestParam(name = "page", defaultValue = "0", required = true) int page,
                        Model model) {
        
        System.out.println("콘 타입 : " + type);
        System.out.println("콘 키워드 : " + keyword);
        System.out.println("콘 페이지 :" + page);

        Page<Users> users = service.userList(type, keyword, page);
        
        Users loginUser = service.getUser(user.getUsername());
        model.addAttribute("user", loginUser);
        model.addAttribute("users",users);

        
        return "/admin/userListForm";
    }

    
}
