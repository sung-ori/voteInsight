package com.kisscotp.voteInsight;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kisscotp.voteInsight.repository.BoardRepository;
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

	// @Test
	// void saveBoardTest() {
	// 	Board b = new Board();

	// 	b.setTitle("제목 예시");
	// 	b.setContents("본문 예시");
	// 	b.setUsername("ㅎㅎㅎ");
	// 	b.setCreatetime(LocalDateTime.now());
	// 	boardRepo.save(b);
		
	// }

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

	//  	user.setName("관리자");
	//  	user.setStudentid("000011000");
	//  	user.setGrade(1L);
	//  	user.setGrouptype(GroupType.ELECTRONICENGINEERING);
	//  	user.setPassword("0000");
	//  	user.setPhone("00000000000");
	//  	user.setRoletype(RoleType.ADMIN);
	//  	user.setAvailable("Y");
	//  	userService.save(user);
		
	//  }
}
