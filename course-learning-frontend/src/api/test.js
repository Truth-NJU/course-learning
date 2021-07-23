import axios from "axios";
import { TEST_MODULE } from "@/api/_prefix";

export const createQuestion = res => {
  console.log(res);
  const {
    question,
    questionAnswer,
    questionAnalysis,
    courseId,
    questionType
  } = res;
  return axios
    .post(`${TEST_MODULE}/questionCreate`, {
      question,
      questionAnswer,
      questionAnalysis,
      courseId,
      questionType
    })
    .then(res => {
      return res.data;
    });
};

export const fixQuestion = res => {
  const {
    questionId,
    question,
    questionAnswer,
    questionAnalysis,
    courseId,
    questionType
  } = res;
  console.log(res);
  return axios.post(`${TEST_MODULE}/questionFix`, {
    questionId,
    question,
    questionAnswer,
    questionAnalysis,
    courseId,
    questionType
  });
};

export const deleteQuestion = res => {
  const { questionId } = res;
  console.log(res);
  return axios.post(`${TEST_MODULE}/questionDelete`, { questionId });
};

export const getQuestionList = res => {
  console.log(res);
  // eslint-disable-next-line no-unused-vars
  const { courseId } = res;
  return axios
    .get(`${TEST_MODULE}/questionGet/course/${courseId}`)
    .then(res => {
      return res.data;
    });
  /*return Promise.resolve({
    data: [
      {
        question: "1+1",
        questionId: 0,
        questionAnswer: "2",
        questionAnalysis: "fw",
        courseId: 6,
        questionType: "ask",
        isRight: null,
        studentAnswer: null
      },
      {
        question: "1+2",
        questionId: 1,
        questionAnswer: "3",
        questionAnalysis: "fw",
        courseId: 6,
        questionType: "ask",
        isRight: null,
        studentAnswer: null
      }
    ]
  });*/
};

export const getTestList = res => {
  const { courseId } = res;
  return axios.get(`${TEST_MODULE}/testGet/course/${courseId}`).then(res => {
    return res.data;
  });
};

export const createTest = res => {
  const { courseId, test, startTime, endTime } = res;
  return axios.post(`${TEST_MODULE}/testCreate`, {
    courseId,
    test,
    startTime,
    endTime
  });
};

export const addQuestion = res => {
  const { testId, questionIdList } = res;
  console.log(questionIdList);
  return axios.post(`${TEST_MODULE}/testAddQuestion`, {
    testId,
    questionIdList
  });
};

export const getTest = res => {
  const { testId } = res;
  return axios.get(`${TEST_MODULE}/questionGet/test/${testId}`, { testId });
};

export const deleteTest = res => {
  const { testId } = res;
  return axios.post(`${TEST_MODULE}/testDelete`, { testId });
};

// 学生，上部分为老师
export const sGetTest = res => {
  const { courseId, userId } = res;
  return axios.get(
    `${TEST_MODULE}/testGet/course/?courseId=${courseId}&userId=${userId}`
  );
};

export const sGetQuestion = res => {
  const { testId, userId } = res;
  return axios.get(
    `${TEST_MODULE}/questionGet/test/?testId=${testId}&userId=${userId}`
  );
};

export const commit = res => {
  const { testId, userId, questionIdAnswers1 } = res;
  let questionIdAnswers = new Object();
  questionIdAnswers1.forEach(function(value, key) {
    questionIdAnswers[key] = value;
  });
  return axios.post(`${TEST_MODULE}/testAnswerCommit`, {
    testId,
    userId,
    questionIdAnswers
  });
};
