package com.kisscotp.voteInsight.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.domain.enums.RoleType;
import com.kisscotp.voteInsight.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UsersRepository userRepo;
    
    @Autowired
    PasswordEncoder encoder;

    

    // 저장
    public void save(Users user) {
    
        userRepo.save(user);
    }

    // 학번으로 유저 한 명을 가져옴
    public Users getUser(String studentid) {
        return userRepo.findByStudentid(studentid);
    }

    // 로그인, 계정 인증 관련
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

    // 계정 만들기
    public Users createUser(String year, Long grade, GroupType group, String name, String phone) {
        Users user = new Users();
        user.setName(name);
        user.setPhone(phone);
        user.setAvailable("Y");
        user.setGrade(grade);
        user.setRoletype(RoleType.USER);
        user.setGrouptype(group);
        // 학번 생성
        user.setStudentid(createStudentId(year, group.getKey()) );
        // 패스워드의 인코딩
        user.setPassword(encoder.encode(user.getStudentid()));

        return user;
    }

    /*
     * 학번 생성기
     * 학번은 입학년도, 학과, 시퀀스 세 가지 요소로 구성할 것이다.
     * 학번, 학과는 관리자에게 입력받는다.
     * 시퀀스는 db를 조회해 가장 큰 시퀀스 보다 1큰 수를 할당할 것이다.
     */
    public String createStudentId(String year, String group) {
        String stdId;   // 출력해줄 학번
        String seq;     // 붙여줄 시퀀스
        System.out.println("검색 학번 : " + year + group);
        // 입학년도, 학과 로 구분해서 입력 받음 ex) 2018 / 02
        // 그 학번으로 검색
        List<Users> userList = userRepo.findByStudentidStartingWith(year+group);
        // 그 중에 가장 마지막 입력된(수가 가장 큰)요소를 선택
        System.out.println("사이즈가 얼마냐?" + userList.size());

        // 201802XXX가 없다면? 2018년에 입학한 02 학과 학생은 없다.
        if (userList.size() <= 0) {
            // 고로, 시퀀스는 001
            seq = "001";
        }
        else {
            Collections.sort(userList, Comparator.comparing(Users::getStudentid));
            String lastStudentId = userList.get(userList.size()-1).getStudentid(); //list 중 가장 큰 시퀀스의 학번
            seq = lastStudentId.substring(lastStudentId.length()-3);    // 시퀀스 3자리만 추출

            Integer intSeq = Integer.parseInt(seq);                     // Integer로 변환해 
            intSeq++;                                                   // 1증가

            if(intSeq / 1000 == 0 ){// 1000으로 나눈 몫이 0이라면? = 3자리수
            
            }
            if(intSeq / 100 == 0 ){ // 100으로 나눈 몫이 0이라면? = 2자리수
                // 앞에 0붙인다.
                seq = "0"+intSeq.toString();
            }
            if(intSeq / 10 == 0 ){// 10으로 나눈 몫이 0이라면? = 1자리수
                // 앞에 00 붙인다
                seq = "00"+intSeq.toString();
            }

        }
        // 입력받은 년도, 학과, 시뭔스 연결해서 반환
        stdId = year+group+seq;

        return stdId;
    }

    public Page<Users> userList(String type, String keyword, int page) {
        // Sort sort = Sort.by("type").descending();

        Pageable pageable = PageRequest.of(page, 10);
        Page<Users> users = null;
        

        if(keyword == null && type == null) {
            users = userRepo.findAll(pageable);
            System.out.println("토탈 갯수 : " + users.getTotalElements()); 
            System.out.println("토탈 페이지 : " + users.getTotalPages()); 
            return users;
        }

        if (type.equals("name")) {
            users = userRepo.findByNameContaining(keyword,pageable);
            return users;
        }

        if (type.equals("studentid")) {
            
            users = userRepo.findByStudentidContaining(keyword,pageable);

            System.out.println("학번 토탈 갯수 : " + users.getTotalElements()); 
            System.out.println("학번 토탈 페이지 : " + users.getTotalPages()); 

            for(Users a: users.getContent()) {
                System.out.println(a.getName());
            }
            return users;
        }

        return users;
    }

    //
    public Users getOneUser(Long useridx) {
        Optional<Users> user =  userRepo.findById(useridx);

        return user.get();
    }
    
    @Transactional
    public void update(Users user) {
        log.debug("야 권한을 알려줘"+user.getRoletype().toString());
        Users findUser =  userRepo.findById(user.getUseridx()).get();

        if (findUser != null) {
            findUser.setRoletype(user.getRoletype());
            findUser.setGrade(user.getGrade());
            findUser.setPhone(user.getPhone());
        }

    }

<<<<<<< HEAD
    public boolean passwordUpdate(String studentid, String password) {
        
        Users user = this.getUser(studentid);

        user.setPassword(encoder.encode(password));

        Users checkUser = userRepo.save(user);

        boolean result = user.equals(checkUser);

        return result;
    }
=======
    //선택학과의 전체 인원 체크
    public int countGroup(GroupType grouptype) {
        return userRepo.countByGrouptype(grouptype);
    }
 
    
>>>>>>> 9f4deedb7dd63b003650bfae2aee5f24dfeabfe4
}
