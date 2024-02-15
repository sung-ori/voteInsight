package com.kisscotp.voteInsight;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.domain.Candidate;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.repository.BoardRepository;
import com.kisscotp.voteInsight.repository.CandidateRepository;
import com.kisscotp.voteInsight.repository.ElectionRepository;
import com.kisscotp.voteInsight.service.UserService;

@SpringBootTest
class VoteInsightApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	@Autowired
	UserService userService;

	@Autowired
	BoardRepository boardRepo;

	@Autowired
	ElectionRepository electionRepo;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CandidateRepository candiRepo;

	//  @Test
	//  void saveUsersTest() {
	//  	// 1. 오라클에서 default 설정해도 적용되지 않는다.
	//  	// 2. 오라클은 시퀀스 방식이다.
	//  	Users user = new Users();

	//  	user.setName("김수한무");
	//  	user.setStudentid("000011000");
	//  	user.setGrade(1L);
	//  	user.setGrouptype(GroupType.ACCOUNTING);
	//  	user.setPassword("1234");
	//  	user.setPhone("01012345678");
	//  	user.setRoletype(RoleType.ADMIN);
	//  	user.setAvailable("Y");

	//  }

	@Test
	void saveBoardTest() {
		Board b = new Board();
		
		b.setTitle("제목 예시");
		b.setContents("본문 예시");
		b.setUsername("ㅎㅎㅎ");
		b.setCreatetime(LocalDateTime.now());
		boardRepo.save(b);
		
	}

	// @Test
	// void joinTest() {
	// 	Users user = new Users();

	// 	user.setName("가입테스트1");
	// 	user.setStudentid("201803404");
	// 	user.setGrade(1L);
	// 	user.setGrouptype(GroupType.ELECTRONICENGINEERING);
	// 	user.setPassword("1234");
	// 	user.setPhone("01012345678");
	// 	user.setRoletype(RoleType.GUEST);
	// 	user.setAvailable("Y");
	// 	userService.save(user);
		
	// }

	//  @Test
	//  void createAdmin() {
		

	//  	Users user = new Users();

	//  	user.setName("김수한무");
	//  	user.setStudentid("000011000");
	//  	user.setGrade(1L);
	//  	user.setGrouptype(GroupType.ELECTRONICENGINEERING);
	//  	user.setPassword(encoder.encode("0000"));
	//  	user.setPhone("00000000000");
	//  	user.setRoletype(RoleType.ADMIN);
	//  	user.setAvailable("Y");
	 	
	// 	userService.save(user);;
		
	//  }


	// 유저 왕창 생성
	//  @Test
	//  void createManyUsers() {
	//  	Users user = new Users();
	//  	// 이름
	// 	String[] a = {"김","이","박","최","홍","성","연"};
	//  	String[] b = {"수","진","열","준","상","주","재"};
	//  	String[] c = {"현","정","호","미","재","영","수"};
	//  	String name = "";

	//  	// 학과 0 ~ 5
	//  	GroupType[] groups = GroupType.values();
	//  	GroupType group;

	//  	// 입학년도
	//  	String[] years = {"2018","2019","2020","2021"};
	//  	String year;

	//  	// 학년
	//  	Long[] grades = {1L, 2L, 3L, 4L};
	//  	Long grade;

	//  	// 전화번호
	//  	String phone = "01067892345";
		
	//  	// int randomA = (int)(Math.random()*6);
	//  	// int randomB = (int)(Math.random()*6);
	//  	// int randomC = (int)(Math.random()*6);
	//  	// int randomG = (int)(Math.random()*4);

	//  	int cnt = 0;
		
	//  	for (int i = 0; i < years.length; i++) {

	//  		year = years[i];

	//  		for(int j = 0; j < groups.length; j++) {

	//  				group = groups[j];

	//  				for (int k = 0; k < 10; k++) {
	//  					int randomA = (int)(Math.random()*6);
	//  					int randomB = (int)(Math.random()*6);
	//  					int randomC = (int)(Math.random()*6);
						
	//  					name = a[randomA] + b[randomB] + c[randomC];

	//  					int randomG = (int)(Math.random()*4);
	//  					grade = grades[randomG];

	//  					user = userService.createUser(year, grade, group, name,phone);
	//  					userService.save(user);
	//  				}

				
	//  		}

	//  	}
	//  }

	// 학번 생성기 테스트
	@Test
	void createStdId() {
		
		Users user = new Users();

		String year = "2018";
		Long grade = 1L;

		GroupType group = GroupType.ACCOUNTING;

		String name = "testName";

		String phone = "99999999999";
		
		user = userService.createUser(year, grade, group, name,phone);
		
		for (int i = 0; i < 20;  i++){
			userService.save(user);
			System.out.println(i+"회 실시");
		}
	}
	
	@Test
	public void createCandidate() {
		String[] std = {"001","002","003","004","005"};
		String[] img = {"1","2","3","4","5"};
		Long[]	num = {1L,2L,3L,4L,5L};
		for (int i = 0; i < 5; i++) {
			String studentid = "201806";

			Candidate candi = new Candidate();
			
			candi.setCandinumber(num[i]);
			candi.setElectionidx(11L);
			candi.setImgpath(img[i]+".jpg");
			candi.setUseridx(userService.getUser(studentid + std[i]).getUseridx());

			candiRepo.save(candi);
		}
	}
}
