package cn.seecoder.courselearning.enums;

import lombok.Getter;

@Getter
public enum TestStatus {
    toStart("未开始", 1),
    Running("进行中", 2),
    Finish("已结束", 3);

    private final String value;

    private final int testStatus;

    TestStatus(String value, int testStatus) {
        this.value = value;
        this.testStatus = testStatus;
    }
}
