<template>
  <div>
    <!-- alert -->
    <v-alert
      class="alert red"
      outlined
      text
      v-show="showAlert"
      transition="scroll-y-transition"
    >
      {{ msg }}
    </v-alert>

    <template>
      <v-dialog v-model="dialog" max-width="400">
        <v-card>
          <v-card-title class="headline">
            账户充值
          </v-card-title>
          <v-text-field
            v-model="value"
            label="充值金额"
            class="pa-6"
          ></v-text-field>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click="handleRecharge">
              充值
            </v-btn>

            <v-btn color="green darken-1" text @click="dialog = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>

    <div>
      <v-card class="ml-1 mt-8 fixed">
        <v-navigation-drawer permanent expand-on-hover width="170">
          <v-list>
            <v-list-item class="px-2">
              <v-list-item-avatar>
                <v-img
                  src="https://randomuser.me/api/portraits/women/85.jpg"
                ></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-subtitle class="text-h6">
                  {{ userInfo.uname }}
                </v-list-item-subtitle>
                <v-list-item-subtitle>
                  {{ userInfo.phone }}
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
          <v-divider></v-divider>

          <v-list nav dense>
            <v-list-item link @click="to_center">
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>个人中心</v-list-item-title>
            </v-list-item>
            <v-list-item link @click="toMyPost">
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>我的帖子</v-list-item-title>
            </v-list-item>
            <v-list-item link @click="toMyReply">
              <v-list-item-icon>
                <v-icon>mdi-folder</v-icon>
              </v-list-item-icon>
              <v-list-item-title>我的回复</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>
      </v-card>
    </div>
    <div>
      <v-container class="pl-lg-16">
        <v-card class="pa-12 mt-8 purple lighten-5">
          <div v-if="show_what === 3">
            <li v-for="(post, key, index) in postLists" :key="index">
              <v-card elevation="1">
                <v-card-title>{{ post.postTitle }}</v-card-title>
                <v-card-text>{{ post.postContent }}</v-card-text>
              </v-card>
            </li>
            <v-pagination
              v-model="pageNoForPost"
              :length="totalPagesForPost"
              @next="getNextPostLists"
              @previous="getPreviousPostLists"
            >
            </v-pagination>
          </div>
          <div v-if="show_what === 4">
            <v-card-title>您有{{totalReplyNum}}个新回复</v-card-title>
            <li v-for="(reply, key, index) in replyLists" :key="index">
              <v-card elevation="1">
                <v-card-title>{{ reply.replyContent }}</v-card-title>
              </v-card>
            </li>
            <v-pagination
              v-model="pageNoForReply"
              :length="totalPagesForReply"
              @next="getNextReplyLists"
              @previous="getPreviousReplyLists"
            >
            </v-pagination>
          </div>
          <div v-if="show_what === 0">
            <v-row justify="space-around">
              <v-avatar size="120">
                <img
                  src="https://cdn.vuetifyjs.com/images/john.jpg"
                  alt="John"
                />
              </v-avatar>
              <v-col class="ml-8">
                <p>{{ userInfo.uname }}</p>
                <p>{{ userInfo.userRole }}</p>
              </v-col>
            </v-row>
            <v-text-field
              v-model="userInfo.phone"
              label="手机号"
              readonly
              class="mt-5"
            ></v-text-field>
            <v-text-field
              v-model="userInfo.createTime"
              label="注册时间"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="userInfo.balance"
              label="账户余额"
              readonly
            ></v-text-field>
            <v-btn color="primary" dark @click.stop="dialog = true">
              充值
            </v-btn>
          </div>
        </v-card>
      </v-container>
    </div>
  </div>
</template>

<script>
import axios from "axios";

import { getUser } from "@/api/user";
import { recharge } from "@/api/recharge";
export default {
  name: "UserCenter",
  components: {},
  data() {
    return {
      userInfo: {
        id: 0,
        uname: "",
        phone: "",
        password: null,
        picture: null,
        balance: 0,
        userRole: "",
        createTime: "",
        isVip: 0,
        vipEndTime: ""
      },
      dialog: false,
      vip_dialog: false,
      showAlert: false,
      value: 0,
      msg: "",
      show_what: 0,
      course: [],
      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"],
      slides: ["尊贵标识", "专有课程"],
      vip_info: [],
      postLists: [],
      pageNoForPost: 1,
      numberForPost: 4,
      totalPagesForPost: 0,
      replyLists: [],
      pageNoForReply: 1,
      numberForReply: 4,
      totalPagesForReply: 0,
      totalReplyNum: 0
    };
  },
  created() {
    let instance = axios.create({
      baseURL: "http://localhost:8081/api",
      timeout: 1000 // 超时时长
    });
    instance
      .get(
        `/post/search/userPost/${this.pageNoForPost}/${
          this.numberForPost
        }?userId=${window.localStorage.getItem("userId")}`
      )
      .then(res => {
        //console.log(res.data);
        this.postLists = res.data.list;
        this.totalPagesForPost = res.data.pages;
        console.log(this.postLists);
      });
    instance
      .get(
        `/post/search/userReply/${this.pageNoForReply}/${
          this.numberForReply
        }?userId=${window.localStorage.getItem("userId")}`
      )
      .then(res => {
        //console.log(res);
        this.replyLists = res.data.list;
        let i = 0;
        for (i = 0; i < this.replyLists.length; i++) {
          this.totalReplyNum += res.data.list[i].newReplyNum;
        }
        this.totalPagesForReply = res.data.pages;
        console.log(this.replyLists);
      });
  },
  methods: {
    getNextReplyLists() {
      //this.pageNoForReply++;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      instance
        .get(
          `/post/search/userReply/${this.pageNoForReply}/${
            this.numberForReply
          }?userId=${window.localStorage.getItem("userId")}`
        )
        .then(res => {
          console.log(res);
          this.replyLists = res.data.list;
          let i = 0;
          for (i = 0; i < this.replyLists.length; i++) {
            this.totalReplyNum += res.data.list[i].newReplyNum;
          }
          this.totalPagesForReply = res.data.pages;
        });
    },
    getPreviousReplyLists() {
      //this.pageNo--;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      instance
        .get(
          `/post/search/userReply/${this.pageNoForReply}/${
            this.numberForReply
          }?userId=${window.localStorage.getItem("userId")}`
        )
        .then(res => {
          console.log(res);
          this.replyLists = res.data.list;
          let i = 0;
          for (i = 0; i < this.replyLists.length; i++) {
            this.totalReplyNum += res.data.list[i].newReplyNum;
          }
          this.totalPagesForReply = res.data.pages;
        });
    },
    getNextPostLists() {
      // this.pageNo++;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      instance
        .get(
          `/post/search/userPost/${this.pageNoForPost}/${
            this.numberForPost
          }?userId=${window.localStorage.getItem("userId")}`
        )
        .then(res => {
          console.log(res.data.list);
          this.postLists = res.data.list;
        });
    },
    getPreviousPostLists() {
      // this.pageNo--;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      instance
        .get(
          `/post/search/userPost/${this.pageNoForPost}/${
            this.numberForPost
          }?userId=${window.localStorage.getItem("userId")}`
        )
        .then(res => {
          console.log(res.data.list);
          this.postLists = res.data.list;
        });
    },
    handleRecharge() {
      recharge({ userId: this.userInfo.id, value: this.value }).then(res => {
        console.log(res);
        if (res && res.code === 1) {
          this.msg = res.msg;
          this.dialog = false;
          this.showAlert = true;
          this.refreshUserInfo();
          setTimeout(() => {
            this.showAlert = false;
          }, 1000);
        }
      });
    },

    refreshUserInfo() {
      const userId = window.localStorage.getItem("userId");
      getUser(userId).then(res => {
        this.userInfo = res || {};
      });
    },

    to_center() {
      this.show_what = 0;
    },
    toMyPost() {
      this.show_what = 3;
    },
    toMyReply() {
      this.show_what = 4;
    }
  },

  mounted() {
    this.refreshUserInfo();
    this.getCourse();
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
.pei {
  width: fit-content;
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 0;
}
.fixed {
  position: fixed;
}
#show {
  float: right;
}
</style>
