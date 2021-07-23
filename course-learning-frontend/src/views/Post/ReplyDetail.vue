<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-card>
        <v-card-text>{{ replyContent }}</v-card-text>
        <v-textarea v-model="replyText" label="请输入回复内容"></v-textarea>
        <v-card-actions @click="reply"> 回复</v-card-actions>
      </v-card>
      <li v-for="(item, key, index) in replyLists" :key="index">
        <v-card elevation="1">
          <v-card-text>{{ item.replyContent }}</v-card-text>
          <v-card-actions @click="jumpToReplyDetail(item)"
            >查看详情</v-card-actions
          >
        </v-card>
      </li>
      <v-pagination
        v-model="pageNo"
        :length="totalPages"
        @next="getNextReplyLists"
        @previous="getPreviousReplyLists"
      >
      </v-pagination>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "ReplyDetail",
  data() {
    return {
      postId: 0,
      pageNo: 1,
      number: 4,
      replyLists: [],
      replyId: 0, //当前回复的id
      replyContent: "",
      totalPages: 0,
      userId: 0, //回复发表用户的id
      replyText: null
    };
  },
  methods: {
    jumpToReplyDetail(item) {
      this.$router.push(
        `/post/replyDetail/${item.replyId}/${item.replyContent}/${item.replyUserId}`
      );
      this.getReplyDetail();
    },
    reply() {
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      if (this.replyText != null) {
        const replyInfo = {
          replyId: 12,
          postId: 0,
          replyUserId: window.localStorage.getItem("userId"),
          repliedUserId: this.userId,
          replyContent: this.replyText,
          repliedId: this.replyId,
          replyTime: new Date(),
          status: 0
        };
        instance.post(`/post/reply/create`, replyInfo).then(res => {
          console.log(res);
          this.getReplyDetail();
          this.replyText = null;
        });
      }
    },
    getNextReplyLists() {
      this.pageNo++;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      instance
        .get(
          `/post/search/reply/${this.pageNo}/${this.number}?repliedId=${this.replyId}&userId=${this.userId}`
        )
        .then(res => {
          console.log(res);
          this.replyLists = res.data.list;
          this.totalPages = res.data.pages;
        });
    },
    getPreviousReplyLists() {
      this.pageNo--;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      instance
        .get(
          `/post/search/reply/${this.pageNo}/${this.number}?repliedId=${this.replyId}&userId=${this.userId}`
        )
        .then(res => {
          console.log(res);
          this.replyLists = res.data.list;
          this.totalPages = res.data.pages;
        });
    },
    getReplyDetail() {
      this.replyId = this.$route.params.replyId;
      this.replyContent = this.$route.params.replyContent;
      this.userId = this.$route.params.replyUserId;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });

      instance
        .get(
          `/post/search/reply/1/${this.number}?repliedId=${this.replyId}&userId=${this.userId}`
        )
        .then(res => {
          console.log(res);
          this.replyLists = res.data.list;
          this.totalPages = res.data.pages;
        });
    }
  },
  created() {
    this.getReplyDetail();
  }
};
</script>

<style scoped></style>
