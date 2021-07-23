package cn.seecoder.courselearning.enums;

import lombok.Getter;

@Getter
public enum QuestionType {
    choice(1, "选择题"),
    ask(2, "问答题");

    private final String value;

    private final int questionType;


    QuestionType(int questionType, String value) {
        this.value = value;

        this.questionType = questionType;
    }
}
