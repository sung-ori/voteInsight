package com.kisscotp.voteInsight.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                boardidx;           // 공지사항 인덱스

    @Column
    private String              title;              // 제목

    @Column
    private String              contents;           // 내용

    @Column
    private String              username;           // 기세자 이름
    
    @Column
    private LocalDateTime       createtime;         // 생성시간

    @Column
    private LocalDateTime       updatetime;         // 수정 시간
}
