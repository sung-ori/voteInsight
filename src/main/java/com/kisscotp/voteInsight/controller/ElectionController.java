package com.kisscotp.voteInsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kisscotp.voteInsight.domain.Election;
import com.kisscotp.voteInsight.domain.ElectionRequestDto;
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

     //선거 목록(관리자용)
   @GetMapping("/listAdmin")
   public String electionlist(@AuthenticationPrincipal UserDetails user,Model model) {
      
       if(user != null) {
           Users loginUser = userService.getUser(user.getUsername());
           model.addAttribute("user", loginUser);
       }
      
       model.addAttribute("elections", electionService.electionlist());
       
       return "/election/electionListAdmin";
   }

    // 선거 생성 폼 
    @GetMapping("/create")
    public String electionCreate(@AuthenticationPrincipal UserDetails user,Model model) {
        if(user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }
        return "/election/electionCreateForm";
    }

    // 선거 생성
    @PostMapping("/create")
    public String electionCreate(Election election, MultipartFile uploadFile) {
        electionService.save(election,uploadFile);

        return "redirect:/election/listAdmin";
    }

      // 선거 삭제
      @PostMapping("/delete/{electionidx}")
      public String boardDelete(@AuthenticationPrincipal UserDetails user,
                                @PathVariable Long electionidx,
                                Model model) {
          if (user != null) {
              Users loginUser = userService.getUser(user.getUsername());
              model.addAttribute("user", loginUser);
          }
  
          electionService.electionDelete(electionidx);
  
          return "redirect:/election/listAdmin";
      }

      //선거 수정폼
      @GetMapping("/update/{electionidx}")
      public String electionUpdateForm(@AuthenticationPrincipal UserDetails user,
                                        @PathVariable (name="electionidx") Long idx,
                                         Model model, 
                                        ElectionRequestDto requestDto){
         if(user != null) {
             Users loginUser = userService.getUser(user.getUsername());
             model.addAttribute("user", loginUser);
         }
            
             Election election = electionService.electionview(idx);

            model.addAttribute("requestDto",requestDto);
            model.addAttribute("election", election);
        
          return "/election/electionUpdateForm";
      }


      //선거 수정 
      @PostMapping("/update/{electionidx}") 
      public String electionUpdate(@PathVariable Long electionidx, 
                                   @ModelAttribute ElectionRequestDto requestDto) { 
      
          electionService.electionUpdate(electionidx, requestDto);
          
          return "redirect:/election/listAdmin"; 
      }


    }   
    
