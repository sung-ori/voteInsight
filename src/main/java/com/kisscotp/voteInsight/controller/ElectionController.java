package com.kisscotp.voteInsight.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import org.springframework.web.bind.annotation.RequestParam;
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

        return "redirect:/election/list";
    }

    // 선거 생성 뷰
    @GetMapping("/write")
    public String electionWrite(@AuthenticationPrincipal UserDetails user,
                                Model model) {
        ElectionRequestDto requestDto = new ElectionRequestDto();
        if (user != null) {
            Users loginUser = userService.getUser(user.getUsername());
            model.addAttribute("user", loginUser);
        }
        model.addAttribute("requestDto", requestDto);
        model.addAttribute("election", new Election());

        return "/election/electionWrite";
    }

    // 선거 생성
    // @PostMapping("/writepro")
    // public String electionWritePro(@ModelAttribute("requestDto") ElectionRequestDto requestDto,
    //                                @RequestParam("file") MultipartFile file) {
    //     try {
    //         String uploadDir = "./posters/";
    //         String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //         Path filePath = Paths.get(uploadDir + fileName);
    //         Files.write(filePath, file.getBytes());

    //         Election election = new Election(requestDto);
    //         election.setPosterpath(fileName);
    //         electionService.electionSave(election);
    //     } catch (IOException e) {
    //         // 파일 처리 중 오류가 발생할 경우 처리할 예외 로직을 여기에 추가하세요.
    //         log.error("Failed to process file upload", e);
    //     }

    //     return "redirect:/election/list";
    // }
}
