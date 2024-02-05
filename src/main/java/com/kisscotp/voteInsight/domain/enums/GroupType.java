package com.kisscotp.voteInsight.domain.enums;

public enum GroupType {
    ACCOUNTING("01","회계학과"),
    ADMINISTRATION("02","행정학과"),
    COMPUTERSCIENCE("03","컴퓨터학부"),
    ELECTRONICENGINEERING("04","전자공학과"),
    PHILOSOPHY("05","철학과"),
    PHYSICS("06","물리학과");


    private String key;
    private String value;
    private int size;

    GroupType(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
    public String getKey(){
        return this.key;
    }

    public static int size(){ return values().length; }
}
