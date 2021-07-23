package cn.seecoder.courselearning.po.course.test;

import cn.seecoder.courselearning.dto.course.CourseTestFinishDTO;

public class CourseUserTestPO {

    // 用户id
    int userId;
    // 测试id
    int testId;
    // 用户测试的分数
    int score;


    public CourseUserTestPO() {
    }

    public CourseUserTestPO(int userId, int testId, int score) {
        this.userId = userId;
        this.testId = testId;
        this.score = score;
    }

    public CourseUserTestPO(int userId, int testId) {
        this.userId = userId;
        this.testId = testId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public CourseUserTestPO(CourseTestFinishDTO courseTestFinishDTO) {
        this.userId = courseTestFinishDTO.getUserId();
        this.testId = courseTestFinishDTO.getTestId();
    }


}
