<template>
  <div>
    <div>
      <v-card class="ml-1 mt-8 fixed">
        <v-navigation-drawer permanent expand-on-hover width="170">
          <v-list nav dense>
            <v-list-item link @click="to_msg">
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>课程信息</v-list-item-title>
            </v-list-item>
            <v-list-item link @click="to_course">
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>课件</v-list-item-title>
            </v-list-item>
            <v-list-item link @click="to_test">
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>测试</v-list-item-title>
            </v-list-item>
            <v-list-item link @click="toPost">
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>课程讨论区</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>
      </v-card>
    </div>
    <v-container class="pl-16 pr-16">
      <!-- alert-->
      <v-alert
        class="alert pink"
        outlined
        type="success"
        text
        v-show="showAlert"
        transition="scroll-y-transition"
      >
        {{ alertMsg }}
      </v-alert>

      <template>
        <v-row>
          <v-dialog v-model="add_question_dialog" max-width="600">
            <v-toolbar color="primary" dark>
              <v-toolbar-title>课程题目</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>

            <v-list subheader two-line>
              <v-list-item
                v-for="question in questions"
                :key="question.questionId"
              >
                <v-list-item-avatar>
                  <v-icon class="grey lighten-1" dark>
                    mdi-file
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title
                    v-text="question.questionType"
                  ></v-list-item-title>

                  <v-list-item-subtitle
                    v-text="question.question"
                  ></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn @click="add_to_test(question.questionId)">
                    添加
                    <v-icon
                      color="grey lighten-1"
                      :class="
                        questionIdList.find(q => q === question.questionId)
                          ? 'green'
                          : ''
                      "
                      >mdi-arrow-up-bold-box-outline</v-icon
                    >
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
            <v-btn @click="add_question_dialog = false">确认</v-btn>
          </v-dialog>
        </v-row>
      </template>

      <!-- 删除 dialog-->
      <template>
        <v-row justify="center">
          <v-dialog v-model="deleteDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">删除课件</span>
              </v-card-title>
              <v-card-text>
                是否确认删除课件 {{ currentEditFile.name }} ?
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="deleteDialog = false">
                  取消
                </v-btn>
                <v-btn
                  color="red darken-1"
                  text
                  @click="handleDeleteCourseWare"
                >
                  删除
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>
      <template>
        <v-row justify="center">
          <v-dialog v-model="deleteQuestionDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">删除题目</span>
              </v-card-title>
              <v-card-text>
                是否确认删除题目 {{ currentQuestion.question }} ?
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="deleteQuestionDialog = false"
                >
                  取消
                </v-btn>
                <v-btn color="red darken-1" text @click="delete_question">
                  删除
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>
      <!-- 编辑 dialog-->
      <template>
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">编辑课件</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件类型"
                        v-model="currentEditFile.fileType"
                        disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件大小"
                        v-model="currentEditFile.fileSize"
                        disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="文件名"
                        v-model="currentEditFile.fileName"
                        disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="课件标题"
                        v-model="currentEditFile.title"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否免费"
                        v-model="currentEditFile.freeFlag"
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否可下载"
                        v-model="currentEditFile.downloadFlag"
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否有权查看"
                        v-model="currentEditFile.availableFlag"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row class="pa-2">
                    <v-btn color="error" @click="showDeleteFileDialog">
                      删除课件
                    </v-btn>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  关闭
                </v-btn>
                <v-btn color="blue darken-1" text @click="handleEditSubmit">
                  保存
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>
      <!-- 题目 -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="question_dialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">创建题目</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col class="d-flex" cols="12" sm="6">
                      <v-text-field
                        label="课程"
                        v-model="courseInfo.name"
                        disabled
                      ></v-text-field>
                    </v-col>
                    <v-col class="d-flex" cols="12" sm="6">
                      <v-select
                        :items="question_types"
                        label="题目类型"
                        v-model="question.type"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-col cols="12">
                    <v-textarea
                      name="question"
                      label="题目"
                      v-model="question.question"
                    ></v-textarea>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      name="answer"
                      label="答案"
                      v-model="question.answer"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      name="any"
                      label="解析"
                      v-model="question.analysis"
                    ></v-textarea>
                  </v-col>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="question_dialog = false"
                >
                  关闭
                </v-btn>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="add_question"
                  v-if="question.type !== ''"
                >
                  保存
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <template>
        <v-row justify="center">
          <v-dialog v-model="current_question" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">修改题目</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-col cols="12">
                    <v-textarea
                      name="question"
                      label="题目"
                      v-model="currentQuestion.question"
                    ></v-textarea>
                  </v-col>
                  <v-col cols="12">
                    <v-text-field
                      name="answer"
                      label="答案"
                      v-model="currentQuestion.answer"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12">
                    <v-textarea
                      name="any"
                      label="解析"
                      v-model="currentQuestion.analysis"
                    ></v-textarea>
                  </v-col>
                  <v-row class="pa-2">
                    <v-btn color="error" @click="deleteQuestionDialog = true">
                      删除题目
                    </v-btn>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="current_question = false"
                >
                  关闭
                </v-btn>
                <v-btn color="blue darken-1" text @click="edit_question">
                  修改
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>
      <!-- 测试 -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="test_dialog" persistent>
            <v-card>
              <v-card-title>
                <span class="headline">创建测试</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-col class="d-flex" cols="12" sm="6">
                    <v-text-field
                      label="课程"
                      v-model="courseInfo.name"
                      disabled
                    ></v-text-field>
                    <v-text-field
                      label="测试名称"
                      v-model="test_name"
                    ></v-text-field>
                  </v-col>
                  <v-row justify="center">
                    <v-card class="ma-2 grey lighten-3">
                      <v-card-title>开始时间</v-card-title>
                      <v-date-picker
                        v-model="start_date"
                        class="mr-3 ml-3"
                        width="260"
                      ></v-date-picker>
                      <v-time-picker
                        v-model="start_time"
                        class="mr-3 ml-3"
                        width="260"
                      ></v-time-picker>
                    </v-card>
                    <v-card class="ma-2 grey lighten-3">
                      <v-card-title>结束时间</v-card-title>
                      <v-date-picker
                        v-model="end_date"
                        class="mr-3 ml-3"
                        width="260"
                      ></v-date-picker>
                      <v-time-picker
                        v-model="end_time"
                        class="mr-3 ml-3"
                        width="260"
                      ></v-time-picker>
                    </v-card>
                  </v-row>
                  <v-list subheader two-line>
                    <v-list-item v-for="id in questionIdList" :key="id">
                      <v-list-item-avatar>
                        <v-icon class="grey lighten-1" dark>
                          mdi-file
                        </v-icon>
                      </v-list-item-avatar>

                      <v-list-item-content>
                        <v-list-item-title
                          v-text="
                            questions.find(q => q.questionId === id)
                              .questionType
                          "
                        ></v-list-item-title>

                        <v-list-item-subtitle
                          v-text="
                            questions.find(q => q.questionId === id).question
                          "
                        ></v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                  <v-btn @click="add_question_dialog = true">编辑题目</v-btn>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="test_dialog = false">
                  关闭
                </v-btn>
                <v-btn color="blue darken-1" text @click="test">
                  创建
                </v-btn>
              </v-card-actions>
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
                  <v-list subheader two-line>
                    <v-list-item
                      v-for="question in currentTest"
                      :key="question.questionId"
                    >
                      <v-list-item-avatar>
                        <v-icon class="grey lighten-1" dark>
                          mdi-file
                        </v-icon>
                      </v-list-item-avatar>

                      <v-list-item-content>
                        <v-list-item-title
                          v-text="question.questionType"
                        ></v-list-item-title>

                        <v-list-item-subtitle
                          v-text="question.question"
                        ></v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                  <v-row class="pa-2">
                    <v-btn color="error" @click="delete_test">
                      删除测试
                    </v-btn>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="test_detail = false">
                  关闭
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>
      <!-- 课件列表 -->
      <v-row v-if="what_show === 1">
        <v-col>
          <v-card max-width="600" class="mx-auto mt-8">
            <v-toolbar color="primary" dark>
              <v-toolbar-title>课程课件</v-toolbar-title>
              <v-spacer></v-spacer>

              <v-file-input
                hide-input
                prepend-icon="mdi-plus"
                v-model="instructionFile"
                @change="handleUploadFile"
              ></v-file-input>
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
                      @click="showEditFileDialog(file.id)"
                      >mdi-more</v-icon
                    >
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
        <v-col>
          <v-card max-width="600" class="mx-auto mt-8">
            <v-toolbar color="primary" dark>
              <v-toolbar-title>课程题目</v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn class="ma-2 blue" dark @click="open_question">
                +
              </v-btn>
            </v-toolbar>

            <v-list subheader two-line>
              <v-list-item
                v-for="question in questions"
                :key="question.questionId"
              >
                <v-list-item-avatar>
                  <v-icon class="grey lighten-1" dark>
                    mdi-file
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title
                    v-text="question.questionType"
                  ></v-list-item-title>

                  <v-list-item-subtitle
                    v-text="question.question"
                  ></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn icon>
                    <v-icon
                      color="grey lighten-1"
                      @click="showCurrentQuestion(question.questionId)"
                      >mdi-more</v-icon
                    >
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
      </v-row>

      <!-- 课程信息 -->
      <v-col v-if="what_show === 0">
        <form class="pa-12 grey lighten-5 mt-8">
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
      <!-- 测试 -->
      <v-card class="mx-auto mt-8" v-if="what_show === 2">
        <v-toolbar color="primary" dark>
          <v-toolbar-title>测试</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn class="ma-2 blue" dark @click="test_dialog = true">
            +
          </v-btn>
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
              <v-btn icon>
                <v-icon color="grey lighten-1" @click="showTest(test.testId)"
                  >mdi-more</v-icon
                >
              </v-btn>
            </v-list-item-action>
          </v-list-item>
        </v-list>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import { uploadFile } from "@/api/file";
import {
  getCourseWareByCourse,
  createCourseWare,
  updateCourseWare,
  deleteCourseWare
} from "@/api/courseWare";
import { getCourseById } from "@/api/course";
import { createQuestion } from "@/api/test";
import { getQuestionList } from "@/api/test";
import { fixQuestion } from "@/api/test";
import { deleteQuestion } from "@/api/test";
import { getTestList } from "@/api/test";
import { createTest } from "@/api/test";
import { addQuestion } from "@/api/test";
import { getTest } from "@/api/test";
import { deleteTest } from "@/api/test";

export default {
  name: "CourseEdit",

  data() {
    return {
      courseInfo: {},
      instructionFile: null,
      types: ["初级", "中级", "高级"],
      question_types: ["ask", "choice"],
      msg: "",
      files: [],
      questions: [],
      tests: [],
      showAlert: false,
      alertMsg: "",
      dialog: false,
      deleteDialog: false,
      question_dialog: false,
      current_question: false,
      deleteQuestionDialog: false,
      test_dialog: false,
      add_question_dialog: false,
      test_detail: false,
      currentEditFile: {
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
      currentQuestion: {
        questionId: 0,
        question: "",
        answer: "",
        analysis: "",
        questionType: ""
      },
      question: {
        id: 0,
        type: "",
        question: "",
        answer: "",
        analysis: ""
      },
      what_show: 0,
      test_name: "",
      start_date: "",
      start_time: "",
      end_time: "",
      end_date: "",
      testList: [],
      questionIdList: [],
      currentTestId: 0,
      currentTest: []
    };
  },

  computed: {
    isCharge: function() {
      return this.courseInfo.cost !== 0;
    }
  },

  created() {
    // console.log(this.$route.params);
  },

  methods: {
    toPost(){
      const { courseId } = this.$route.params;
      this.$router.push(`/post/coursePost/${courseId}`);
    },
    async handleUploadFile() {
      const formData = new window.FormData();
      formData.append("file", this.instructionFile);
      const res = await uploadFile(formData);
      if (res.code === 1) {
        const { courseId } = this.$route.params;
        const fileInfo = {
          courseId: courseId,
          number: this.files.length,
          title: res.data.name,
          fileName: res.data.name,
          fileType: res.data.type,
          fileSize: res.data.size,
          freeFlag: true,
          downloadFlag: true,
          availableFlag: true
        };
        createCourseWare(fileInfo).then(res => {
          console.log(res);
          this.alertMsg = res.msg;
          this.showAlert = true;
          setTimeout(() => {
            this.showAlert = false;
            this.refreshFile();
          }, 1000);
        });
      }
    },

    refreshFile() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseWareByCourse({ courseId, uid }).then(res => {
        this.files = res || [];
      });
    },

    getQuestions() {
      const { courseId } = this.$route.params;
      getQuestionList({ courseId }).then(res => {
        this.questions = res.data;
      });
    },

    loadCourse() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseById({ uid, courseId }).then(res => {
        this.courseInfo = res;
        console.log(this.courseInfo);
      });
    },

    showEditFileDialog(courseWareId) {
      const currentFile = this.files.find(f => f.id === courseWareId);
      console.log(currentFile);
      this.currentEditFile = currentFile;
      this.dialog = true;
    },

    showCurrentQuestion(id) {
      const question = this.questions.find(f => f.questionId === id);
      this.currentQuestion = {
        questionId: question.questionId,
        question: question.question,
        answer: question.questionAnswer,
        analysis: question.questionAnalysis,
        questionType: question.questionType
      };
      this.current_question = true;
    },
    edit_question() {
      const ha = this.currentQuestion;
      if (ha.question === "" || ha.answer === "" || ha.answer === null) {
        alert("题目信息不能有空");
        return;
      }
      fixQuestion({
        questionId: ha.questionId,
        question: ha.question,
        questionAnswer: ha.answer,
        questionAnalysis: ha.analysis,
        questionType: ha.questionType
      }).then(res => {
        this.current_question = false;
        this.alertMsg = res.msg;
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
          this.getQuestions();
        }, 1000);
      });
    },

    handleEditSubmit() {
      updateCourseWare(this.currentEditFile).then(res => {
        this.dialog = false;
        console.log(res);
        this.alertMsg = res.msg;
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
          this.refreshFile();
        }, 1000);
      });
    },

    showDeleteFileDialog() {
      this.deleteDialog = true;
    },

    handleDeleteCourseWare() {
      deleteCourseWare(this.currentEditFile.id).then(res => {
        this.alertMsg = res.msg;
        this.deleteDialog = false;
        this.dialog = false;
        this.showAlert = true;
        this.refreshFile();
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
      });
    },
    delete_question() {
      deleteQuestion(this.currentQuestion.questionId).then(res => {
        this.alertMsg = res.msg;
        this.deleteQuestionDialog = false;
        this.currentQuestion = false;
        this.showAlert = true;
        this.getQuestions();
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
      });
    },
    to_msg() {
      this.what_show = 0;
    },
    to_course() {
      this.what_show = 1;
    },
    to_test() {
      this.what_show = 2;
    },
    open_question() {
      console.log(this.courseInfo);
      this.question_dialog = !this.question_dialog;
    },
    add_question() {
      this.question.id = this.courseInfo.id;
      const ha = this.question;
      if (ha.question === "" || ha.answer === "" || ha.answer === null) {
        alert("题目信息不能有空");
        return;
      }
      createQuestion({
        question: ha.question,
        questionAnswer: ha.answer,
        questionAnalysis: ha.analysis,
        courseId: ha.id,
        questionType: ha.type
      }).then(res => {
        this.question_dialog = false;
        this.alertMsg = res.msg;
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
          this.getQuestions();
        }, 1000);
      });
    },

    getTests() {
      const { courseId } = this.$route.params;
      getTestList({ courseId }).then(res => {
        this.tests = res.data;
      });
    },
    async test() {
      const start = this.start_date + "T" + this.start_time + ":00.000Z";
      const end = this.end_date + "T" + this.end_time + ":00.000Z";
      if (this.questionIdList.length === 0) {
        alert("题目为空");
        return;
      }
      if (start === "" || end === "" || this.test_name === "") {
        alert("有内容为空");
        return;
      }
      if (start > end) {
        alert("时间设置错误");
        return;
      }
      let id;
      await createTest({
        courseId: this.courseInfo.id,
        test: this.test_name,
        startTime: start,
        endTime: end
      }).then(res => {
        id = res.data.data.testId;
      });
      addQuestion({testId: id, questionIdList: this.questionIdList}).then(
          res => {
            this.test_dialog = false;
            this.showAlert = true;
            this.alertMsg = res.msg;
            this.getTests();
            setTimeout(() => {
              this.showAlert = false;
            }, 1000);
          }
      );
    },
    add_to_test(id) {
      const index = this.questionIdList.indexOf(id);
      if (index === -1) {
        this.questionIdList.push(id);
      } else {
        this.questionIdList.splice(index, 1);
      }
      console.log(this.questionIdList);
    },
    showTest(id) {
      this.test_detail = true;
      this.currentTestId = id;
      console.log(id);
      getTest({ testId: id }).then(res => {
        this.currentTest = res.data.data;
        console.log(this.currentTest);
      });
    },
    delete_test() {
      deleteTest({ testId: this.currentTestId }).then(res => {
        this.test_detail = false;
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
    this.getQuestions();
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
