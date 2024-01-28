package com.kisscotp.voteInsight.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "투표자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
