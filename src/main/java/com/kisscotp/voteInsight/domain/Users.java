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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VI_USERS")
@SequenceGenerator(
    name = "users_seq",             // 임의로 정하는 jpa에서 부를 이름
    sequenceName = "VI_USERS_SEQ",  // 실제 DB의 사용할 시퀀스 이름
    initialValue = 1,               // 초기값
    allocationSize = 1              // 한번에 할당받을 값의 갯수
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq") // MySql 은 IDENTITY
    private Long        useridx;       // 인덱스

    @Column
    private String      name;           // 이름

    @Column
    private String      studentid;      // 학번
    
    @Column
    private String      password;       // 비밀번호

    @Enumerated(EnumType.STRING)
    @Column
    private GroupType   grouptype;     // 학과(그룹 이름)

    @Column
    private String      phone;          // 전화번호

    @Column
    private Long        grade;          // 학년

    @Enumerated(EnumType.STRING)
    @Column
    private RoleType    roletype;      // 권한

    @Column
    private String      available;      // 사용가능 여부

    // 일단 따라서 만들어놨습니다. 
    public void updateRole(RoleType roleType) {
        this.roletype = roleType;
    }
    public String getRoleKey() {
        return this.roletype.getKey();
    }
}
