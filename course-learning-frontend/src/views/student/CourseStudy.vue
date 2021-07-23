<template>
  <div>
    <v-card class="ml-1 mt-8 fixed">
      <v-navigation-drawer permanent expand-on-hover width="170">
        <v-list nav dense>
          <v-list-item link @click="what_show = 0">
            <v-list-item-icon>
              <v-icon>mdi-folder</v-icon>
            </v-list-item-icon>
            <v-list-item-title>课程信息</v-list-item-title>
          </v-list-item>
          <v-list-item link @click="what_show = 1">
            <v-list-item-icon>
              <v-icon>mdi-folder</v-icon>
            </v-list-item-icon>
            <v-list-item-title>课件</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    </v-card>
    <v-container class="pl-16 pr-16">
      <!-- alert-->
      <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showAlert"
        transition="scroll-y-transition"
      >
        {{ alertMsg }}
      </v-alert>

      <!-- dialog-->
      <template>
        <v-btn color="blue darken-1" @click="jumpToTalkArea">
          课程讨论区
        </v-btn>
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">课件详情</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件类型"
                        v-model="currentFile.fileType"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件大小"
                        v-model="currentFile.fileSize"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="文件名"
                        v-model="currentFile.fileName"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="课件标题"
                        v-model="currentFile.title"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否免费"
                        v-model="currentFile.freeFlag"
                        readonly
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否可下载"
                        v-model="currentFile.downloadFlag"
                        readonly
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否有权查看"
                        v-model="currentFile.availableFlag"
                        readonly
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  Close
                </v-btn>
                <v-btn
                  v-show="currentFile.downloadFlag"
                  color="blue darken-1"
                  text
                  @click="handleDownloadFile"
                >
                  下载课件
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 考试-->
      <template>
        <v-row>
          <v-dialog v-model="my_test" v-if="!currentTest.isFinished">
            <v-card>
              <v-card-title>{{ currentTest.testName }}</v-card-title>
              <v-col>
                <v-card
                  v-for="question in currentQuestion"
                  :key="question.questionId"
                  class="ma-2 pa-3 grey lighten-3"
                >
                  <v-card-title>
                    {{ question.question + "(" + question.questionType + ")" }}
                  </v-card-title>
                  <v-text-field
                    label="答案"
                    v-model="
                      answers.find(a => a.questionId === question.questionId)
                        .answer
                    "
                    @blur="
                      done(
                        question.questionId,
                        answers.find(a => a.questionId === question.questionId)
                          .answer
                      )
                    "
                  ></v-text-field>
                </v-card>
              </v-col>
              <v-card-actions>
                <v-btn @click="submit">查看</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <v-dialog v-model="my_test" v-if="currentTest.isFinished">
            <v-card>
              <v-card-title>{{ currentTest.testName }}</v-card-title>
              <v-col>
                <v-card
                  v-for="question in currentQuestion"
                  :key="question.questionId"
                  class="ma-2 pa-3 grey lighten-3"
                >
                  <v-card-title>
                    {{ question.question + "(" + question.questionType + ")" }}
                  </v-card-title>
                  <v-row>
                    <v-text-field
                      label="我的答案"
                      disabled
                      v-model="question.studentsAnswer"
                    ></v-text-field>
                    <v-text-field
                      label="正确答案"
                      disabled
                      v-model="question.questionAnswer"
                    ></v-text-field>
                    <v-text-field
                      label="是否正确"
                      disabled
                      v-model="question.isRight"
                    ></v-text-field>
                  </v-row>
                  <v-textarea
                    label="解析"
                    disabled
                    v-model="question.questionAnalysis"
                  >
                  </v-textarea>
                  <v-text-field
                    label="成绩"
                    disabled
                    v-model="currentTest.score"
                  >
                  </v-text-field>
                </v-card>
              </v-col>
            </v-card>
          </v-dialog>
        </v-row>
      </template>
      <template>
        <v-row justify="center">
          <v-dialog v-model="test_detail" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">测试信息</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-text-field
                      label="测试名称"
                      v-model="currentTest.testName"
                      disabled
                    ></v-text-field>
                    <v-text-field
                      label="是否完成"
                      v-model="currentTest.isFinished"
                      disabled
                    ></v-text-field>
                  </v-row>
                  <v-row>
                    <v-text-field
                      label="开始时间"
                      v-model="currentTest.startTime"
                      disabled
                    ></v-text-field>
                    <v-text-field
                      label="结束时间"
                      v-model="currentTest.endTime"
                      disabled
                    ></v-text-field>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  @click="my_test_detail"
                  v-if="currentTest.testStatus === 'Running'||currentTest.testStatus==='Finish'"
                  >{{ currentTest.isFinished ? "查看" : "参加考试" }}</v-btn
                >
                <v-btn color="blue darken-1" @click="my_test_detail" v-else disabled>{{
                  currentTest.isFinished ? "查看" : "参加考试"
                }}</v-btn>
                <v-btn color="blue darken-1" @click="test_detail = false">
                  关闭
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <v-row v-if="what_show === 1">
        <!-- 课件 -->
        <v-col>
          <v-card max-width="600" class="mx-auto mt-8">
            <v-toolbar color="indigo lighten-2" dark>
              <v-app-bar-nav-icon></v-app-bar-nav-icon>
              <v-toolbar-title>下载课件</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>

            <v-list subheader two-line>
              <v-list-item v-for="file in files" :key="file.title">
                <v-list-item-avatar>
                  <v-icon class="grey lighten-1" dark>
                    mdi-file
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title v-text="file.title"></v-list-item-title>

                  <v-list-item-subtitle
                    v-text="file.fileSize"
                  ></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn icon>
                    <v-icon
                      color="grey lighten-1"
                      @click="showFileDialog(file.id)"
                      >mdi-more</v-icon
                    >
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
        <!-- 测试 -->
        <v-col>
          <v-card max-width="600" class="mx-auto mt-8">
            <v-toolbar color="primary" dark>
              <v-toolbar-title>课程题目</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-list subheader two-line>
              <v-list-item v-for="test in tests" :key="test.testId">
                <v-list-item-avatar>
                  <v-icon class="grey lighten-1" dark>
                    mdi-file
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title v-text="test.testName"></v-list-item-title>

                  <v-list-item-subtitle
                    v-text="test.testStatus"
                  ></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn icon @click="show_test(test.testId)">
                    <v-icon color="grey lighten-1">mdi-more</v-icon>
                    详情
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
      </v-row>
      <v-col v-if="what_show === 0">
        <form class="pa-12 grey lighten-5 mt-8" style="position: sticky;top: 0">
          <v-text-field
            v-model="courseInfo.name"
            label="课程名称"
            readonly
          ></v-text-field>
          <v-text-field
            v-model="courseInfo.id"
            label="课程ID"
            readonly
          ></v-text-field>
          <v-select
            :items="types"
            v-model="courseInfo.type"
            label="课程分类"
            readonly
          ></v-select>
          <v-textarea
            v-model="courseInfo.intro"
            label="课程简介"
            readonly
          ></v-textarea>
          <v-text-field
            v-model="courseInfo.school"
            label="所属学校"
            readonly
          ></v-text-field>
          <v-text-field
            v-model="courseInfo.createTime"
            label="创建时间"
            readonly
          ></v-text-field>
          <v-text-field
            v-model="courseInfo.teacherName"
            label="创建教师"
            readonly
          ></v-text-field>
          <v-switch
            v-model="isCharge"
            :label="`是否为付费课程: ${isCharge.toString()}`"
            readonly
          ></v-switch>
          <v-text-field
            v-show="isCharge"
            v-model="courseInfo.cost"
            label="课程价格(元)"
            readonly
          ></v-text-field>
        </form>
      </v-col>
    </v-container>
  </div>
</template>

<script>
import { getCourseWareByCourse } from "@/api/courseWare";
import { getCourseById } from "@/api/course";
import { FILE_BASE_PATH } from "@/api/file";
import { sGetTest } from "@/api/test";
import { sGetQuestion } from "@/api/test";
import { commit } from "@/api/test";

export default {
  name: "CourseEdit",

  data() {
    return {
      my_test: false,
      test_detail: false,
      what_show: 0,
      courseInfo: {},
      types: ["初级", "中级", "高级"],
      msg: "",
      files: [],
      showAlert: false,
      alertMsg: "",
      dialog: false,
      currentFile: {
        id: 0,
        courseId: 0,
        number: 0,
        title: "",
        fileName: "",
        fileType: "",
        fileSize: "",
        freeFlag: false,
        downloadFlag: true,
        availableFlag: false,
        uploadTime: ""
      },
      tests: [],
      currentTest: {},
      currentQuestion: [],
      answers: [],
      answersMap: {}
    };
  },

  computed: {
    isCharge: function() {
      return this.courseInfo.cost !== 0;
    }
  },

  methods: {
    refreshFile() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseWareByCourse({ courseId, uid }).then(res => {
        this.files = res || [];
      });
    },

    loadCourse() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseById({ uid, courseId }).then(res => {
        this.courseInfo = res;
      });
    },

    jumpToTalkArea() {
      const { courseId } = this.$route.params;
      this.$router.push(`/post/coursePost/${courseId}`);
    },

    showFileDialog(courseWareId) {
      const currentFile = this.files.find(f => f.id === courseWareId);
      console.log(currentFile);
      this.currentFile = currentFile;
      this.dialog = true;
    },

    handleDownloadFile() {
      // downloadFile(this.currentFile.fileName).then((res) => {
      //   console.log(res);
      //   this.alertMsg = "下载成功！";
      //   this.showAlert = true;
      //   this.dialog = false;
      //   setTimeout(() => {
      //     this.showAlert = false;
      //   }, 1000);
      // });
      window.open(`${FILE_BASE_PATH}/${this.currentFile.fileName}`);
    },
    getTests() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      sGetTest({ courseId: courseId, userId: uid }).then(res => {
        this.tests = res.data.data;
      });
    },
    show_test(id) {
      this.currentTest = this.tests.find(f => f.testId === id);
      this.test_detail = true;
    },
    my_test_detail() {
      const uid = window.localStorage.getItem("userId");
      sGetQuestion({ testId: this.currentTest.testId, userId: uid }).then(
        res => {
          this.currentQuestion = res.data.data;
          this.answersMap = new Map();
          for (let i = 0; i < this.currentQuestion.length; i++) {
            if (this.currentQuestion[i].questionAnswer === null) {
              this.currentQuestion[i].questionAnswer = "答案暂未公布";
              this.currentQuestion[i].questionAnalysis = "答案暂未公布";
            }
            this.answersMap.set(this.currentQuestion[i].questionId + "", "");
            this.answers.push({
              questionId: this.currentQuestion[i].questionId,
              answer: ""
            });
          }
          console.log(res);
        }
      );
      this.my_test = true;
    },
    done(questionId, answer) {
      this.answersMap.set(questionId + "", answer);
    },
    submit() {
      const uid = window.localStorage.getItem("userId");
      commit({
        testId: this.currentTest.testId,
        userId: parseInt(uid),
        questionIdAnswers1: this.answersMap
      }).then(res => {
        this.my_test = false;
        this.showAlert = true;
        this.alertMsg = res.msg;
        this.getTests();
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
      });
    }
  },

  mounted() {
    this.refreshFile();
    this.loadCourse();
    this.getTests();
  }
};
</script>

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
.fixed {
  position: fixed;
}
</style>
