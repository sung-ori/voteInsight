package com.kisscotp.voteInsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VoteInsightApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteInsightApplication.class, args);
	}

}
