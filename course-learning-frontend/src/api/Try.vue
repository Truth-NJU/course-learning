<template>
  <div>
    try
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Try.vue",
  data() {
    return {
      // TODO 以下为CourseTestController请求所需的数据
      courseId: 1,
      testId: 1,
      userId: 2,
      teacherId: 1,
      question: "丁笑宇大傻逼",
      questionId: 1,
      questionAnswer: "",
      questionAnalysis: "",
      questionType: "ask",
      score: 0,
      isFinished: false,
      questionIdAnswers: null,
      test: "期中考试",
      startTime: new Date(), //测试开始时间
      endTime: new Date(), //测试结束时间
      status: "toStart", //测试状态
      //TODO 以下为PostController请求所需的数据
      postId: 0,
      postTitle: "金童基督教文化", //帖子标题
      postTime: new Date(), //帖子发布时间
      postContent: "喝完就饿就好", //帖子内容
      pageNo: 2, //页数
      number: 3 //每页显示的数量
    };
  },
  created() {
    let instance = axios.create({
      baseURL: "http://localhost:8081/api",
      timeout: 1000 // 超时时长
    });
    // TODO 以下为CourseTest所需的请求
    /* 教师创建题目
    const questionCreate = {
      courseId: this.courseId,
      question: this.question,
      questionId: this.questionId,
      questionAnswer: this.questionAnswer,
      questionAnalysis: this.questionAnalysis,
      questionType: this.questionType
    };
    instance.post(`/course-test/questionCreate`, questionCreate).then(res => {
      console.log(res);
    });
    // 更新题目
    const questionFix = {
      courseId: this.courseId,
      question: this.question,
      questionId: 7,
      questionAnswer: this.questionAnswer,
      questionAnalysis: this.questionAnalysis,
      questionType: this.questionType
    };
    instance.post(`/course-test/questionFix`, questionFix).then(res => {
      console.log(res);
    });
    // 删除questionId对应的题目
    const questionDelete = {
      courseId: this.courseId,
      question: this.question,
      questionId: 7,
      questionAnswer: this.questionAnswer,
      questionAnalysis: this.questionAnalysis,
      questionType: this.questionType
    };
    instance.post(`/course-test/questionDelete`, questionDelete).then(res => {
      console.log(res);
    });
    // 根据课程id获得课程题目
    instance
      .get("/course-test/questionGet/course/" + this.courseId)
      .then(res => {
        console.log(res.data);
      });
    // 根据测试id获得测试题目
    instance.get(`/course-test/questionGet/test/${this.testId}`).then(res => {
      console.log(res.data);
    });
    // 根据教师id获得教师发布的测试列表
    instance.get(`/course-test/testGet/teacher/${this.teacherId}`).then(res => {
      console.log(res.data);
    });
    // 根据课程id获得课程测试列表
    instance.get(`/course-test/testGet/course/${this.courseId}`).then(res => {
      console.log(res.data);
    });
    // 为测试选择题目
    const testAddQuestion = {
      testId: this.testId,
      questionIdList: [1, 2, 3]
    };
    instance.post(`/course-test/testAddQuestion`, testAddQuestion).then(res => {
      console.log(res);
    });
    // TODO 提交测试答案并计算分数
    const testAnswerCommit = {
      testId: this.testId,
      userId: this.userId,
      // 注意这儿要传如一个map<questionId,questionAnswer>
      questionIdAnswers: this.questionIdAnswers,
      score: this.score,
      isFinished: true
    };
    instance
      .post(`/course-test/testAnswerCommit`, testAnswerCommit)
      .then(res => {
        console.log(res);
      });
    //创建测试
    const testCreate = {
      courseId: this.courseId,
      testId: this.testId,
      test: this.test,
      questionsId: [1, 2, 3],
      startTime: this.startTime,
      endTime: this.endTime,
      status: this.status // 应传入枚举类型
    };
    instance.post(`/course-test/testCreate`, testCreate).then(res => {
      console.log(res);
    });
    // 更新测试
    const testFix = {
      courseId: this.courseId,
      testId: 10,
      test: "考试",
      questionsId: [1, 2,3],
      startTime: this.startTime,
      endTime: this.endTime,
      status: this.status // 应传入枚举类型
    };
    instance.post(`/course-test/testFix`, testFix).then(res => {
      console.log(res);
    });
    // 删除测试 这儿只要准确传入testId即可，其余可以随便传
    const testDelete = {
      courseId: this.courseId,
      testId: 10,
      test: "考试",
      questionsId: [1, 2, 3],
      startTime: this.startTime,
      endTime: this.endTime,
      status: this.status // 应传入枚举类型
    };
    instance.post(`/course-test/testDelete`, testDelete).then(res => {
      console.log(res);
    });
    //TODO 以下为PostController请求所需的数据
    //创建帖子
    const post = {
      postId: this.postId,
      userId: this.userId,
      courseId: this.courseId,
      title: this.postTitle,
      postTime: this.postTime,
      content: this.postContent
    };
    instance.post(`/post/create`, post).then(res => {
      console.log(res);
    });*/
    // 查看帖子
    instance
      .get(`/post/search/${this.courseId}/${this.pageNo}/${this.number}`)
      .then(res => {
        console.log(res);
      });
  }
};
</script>

<style scoped></style>
