package cn.seecoder.courselearning.enums;

import lombok.Getter;

@Getter
public enum HttpStatus {
    COMMON_OK("ok", 200, 2001),

    USER_LOGIN_CODE_ERROR("code error ", 200, 6001),
    USER_LOGIN_USERNAME_ERROR("empty user", 200, 6002),
    USER_REGISTER_USERNAME_REPEAT("repeat username", 200, 6003),

    USER_MONEY_NOT_ENOUGH("don't have enough money", 200, 6004),

    TEST_USER_ROLE_ERROR("only student is allowed to finish test", 200, 6005),

    COURSE_NOT_FOUND("course not found in database",200,6006),

    TEST_NOT_FOUND("test not found in database",200,6007),

    QUESTION_NOT_FOUND("question not found in database",200,6008),

    USER_NOT_FOUND("user not found in database",200,6009),

    DATA_INTEGRALITY_ERROR("data not complete", 200, 6000);


    private final String description;

    private final int httpStatus;

    private final int code;

    HttpStatus(String description, int httpStatus, int code) {
        this.description = description;
        this.httpStatus = httpStatus;
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpStatus{" +
                "description='" + description + '\'' +
                ", httpStatus=" + httpStatus +
                ", code=" + code +
                '}';
    }
    }
