package com.kisscotp.voteInsight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UsersRepository userRepo;
    
    @Autowired
    PasswordEncoder encoder;

    public void save(Users user) {
        // 패스워드의 인코딩
        user.setPassword(encoder.encode(user.getPassword()));
        // 현재는 엔터티를 사용하지만 dto를 만들고 빌더를 사용하는 방식으로 수정하는 것이 좋을 지도,,?
        userRepo.save(user);
    }

    // 학번으로 유저 한 명을 가져옴
    public Users getUser(String studentid) {
        return userRepo.findByStudentid(studentid);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String studentid) throws UsernameNotFoundException {
        Users user = userRepo.findByStudentid(studentid);
        if (user == null) {
            throw new UsernameNotFoundException("해당하는 학번 없음 : " + studentid);
        }
        return  toUserDetail(user);
        
    }

    private UserDetails toUserDetail(Users user) {
        return User.builder()
            .username(user.getStudentid())
            .password(user.getPassword())
            .roles(user.getRoletype().toString())// Enum 활용
            .build();
    }
    
}