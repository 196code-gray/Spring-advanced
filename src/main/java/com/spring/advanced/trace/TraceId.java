package com.spring.advanced.trace;

import java.util.UUID;
// 요청 Id, level을 가지는 class
public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int i) {
        this.id = id;
        this.level = i;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8); // 앞부분만 사용
    }

    // 다음 로그 레벨을 위한 메서드
    public TraceId createNextId(){
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId(){
        return new TraceId(id, level -1);
    }

    // 현재 레벨이 첫번째 레벨인지 체크
    public boolean isFirstLevel(){
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
