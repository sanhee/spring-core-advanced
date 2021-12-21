package com.example.springcoreadvanced.trace;

import java.util.UUID;

// 로그 추적기는 트랜잭션ID와 깊이를 표현하는 방법이 필요함
public class TraceId {
    private String id; // 트랜잭션 아이디
    private int level; // 트랜잭션 레벨

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // 로그 기능으로 중복이 되도 크게 상관없기 때문에, 실용적인 UUID 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
