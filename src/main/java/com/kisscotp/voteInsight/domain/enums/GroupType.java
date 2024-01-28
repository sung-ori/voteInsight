package com.kisscotp.voteInsight.domain.enums;

public enum GroupType {
    ACCOUNTING("회계학과"),
    ADMINISTRATION("행정학과"),
    COMPUTERSCIENCE("컴퓨터학부"),
    ELECTRONICENGINEERING("전자공학과"),
    PHILOSOPHY("철학과"),
    PHYSICS("물리학과");

    private String value;
    private int size;

    GroupType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static int size(){ return values().length; }
}
