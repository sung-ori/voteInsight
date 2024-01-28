package com.kisscotp.voteInsight.domain;

import com.kisscotp.voteInsight.domain.enums.GroupType;
import com.kisscotp.voteInsight.domain.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        ueseridx;       // 인덱스

    @Column
    private String      name;           // 이름

    @Column
    private String      studentid;      // 학번
    
    @Column
    private String      password;       // 비밀번호

    @Enumerated(EnumType.STRING)
    @Column
    private GroupType   groupType;     // 학과(그룹 이름)

    @Column
    private String      phone;          // 전화번호

    @Column
    private Long        grade;          // 학년

    @Enumerated(EnumType.STRING)
    @Column
    private RoleType    roleType;      // 권한

    @Column
    private String      available;      // 사용가능 여부

    // 일단 따라서 만들어놨습니다. 
    public void updateRole(RoleType roleType) {
        this.roleType = roleType;
    }
    public String getRoleKey() {
        return this.roleType.getKey();
    }
}
