package com.kisscotp.voteInsight;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kisscotp.voteInsight.domain.Board;
import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.domain.enums.RoleType;
import com.kisscotp.voteInsight.repository.BoardRepository;
import com.kisscotp.voteInsight.repository.UsersRepository;

@SpringBootTest
class VoteInsightApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	UsersRepository usersRepo;

	@Autowired
	BoardRepository boardRepo;

	@Test
	void saveUsersTest() {
		// 1. 오라클에서 default 설정해도 적용되지 않는다.
		// 2. 오라클은 시퀀스 방식이다.
		Users user = new Users();

		user.setName("박수한무");
		user.setStudentid("201808404");
		user.setGrade(1L);
		user.setGrouptype(GroupType.ACCOUNTING);
		user.setPassword("1234");
		user.setPhone("01012345678");
		user.setRoletype(RoleType.GUEST);
		user.setAvailable("Y");

		this.usersRepo.save(user);
	}

	@Test
	void saveBoardTest() {
		Board b = new Board();

		b.setTitle("제목 예시");
		b.setContents("본문 예시");
		b.setUsername("ㅎㅎㅎ");

		boardRepo.save(b);
		
	}
}
