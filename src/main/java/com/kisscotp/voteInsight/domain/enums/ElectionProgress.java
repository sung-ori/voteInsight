package com.kisscotp.voteInsight.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ElectionProgress {
    R("준비중"),
    G("투표중"),
    V("열람 가능"),
    E("종료");

    private String progress;

    ElectionProgress(String progress) {
        this.progress = progress;

    }
    public String getProgress() {
        return this.progress;
    }
}
