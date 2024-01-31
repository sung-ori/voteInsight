package com.kisscotp.voteInsight;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kisscotp.voteInsight.domain.Users;
import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.domain.enums.RoleType;
import com.kisscotp.voteInsight.repository.UsersRepository;

@SpringBootTest
class VoteInsightApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	UsersRepository usersRepository;

	@Test
	void saveUsersTest() {
		// 1. 오라클에서 default 설정해도 적용되지 않는다.
		// 2. 오라클은 시퀀스 방식이다.
		// 3. 
		Users user = new Users();

		user.setName("박수한무");
		user.setStudentid("201808404");
		user.setGrade(1L);
		user.setGrouptype(GroupType.ACCOUNTING);
		user.setPassword("1234");
		user.setPhone("01012345678");
		user.setRoletype(RoleType.GUEST);
		user.setAvailable("Y");

		this.usersRepository.save(user);

	}
}
