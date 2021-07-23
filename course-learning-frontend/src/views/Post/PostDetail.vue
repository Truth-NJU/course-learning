<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-card>
        <v-card-title>{{ postTitle }}</v-card-title>
        <v-card-text>{{ postContent }}</v-card-text>
        <v-textarea v-model="replyContent" label="请输入回复内容"></v-textarea>
        <v-card-actions @click="reply"> 回复</v-card-actions>
      </v-card>
      <li v-for="(item, key, index) in replyLists" :key="index">
        <v-card elevation="1">
          <v-card-text>{{ item.replyContent }}</v-card-text>
          <v-card-actions @click="jumpToReplyDetail(item)">查看详情</v-card-actions>
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
  name: "PostDetail",
  data() {
    return {
      postTitle: "",
      postContent: "",
      postId: 0,
      pageNo: 1,
      number: 4,
      replyLists: [],
      replyContent: null,
      totalPages: 0,
      userId: 0 //帖子发表用户的id
    };
  },
  methods: {
    jumpToReplyDetail(item) {
      this.$router.push(`/post/replyDetail/${item.replyId}/${item.replyContent}/${item.replyUserId}`);
    },
    reply() {
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      if (this.replyContent != null) {
        const replyInfo = {
          replyId: 12,
          postId: this.postId,
          replyUserId: window.localStorage.getItem("userId"),
          repliedUserId: this.userId,
          replyContent: this.replyContent,
          repliedId: 0,
          replyTime: new Date(),
          status: 0
        };
        instance.post(`/post/reply/create`, replyInfo).then(res => {
          console.log(res);
          this.getPostDetail();
          this.replyContent = null;
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
          `/post/search/post/${this.pageNo}/${this.number}?postId=${
            this.postId
          }&userId=${window.localStorage.getItem("userId")}`
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
          `/post/search/post/${this.pageNo}/${this.number}?postId=${
            this.postId
          }&userId=${window.localStorage.getItem("userId")}`
        )
        .then(res => {
          console.log(res);
          this.replyLists = res.data.list;
          this.totalPages = res.data.pages;
        });
    },
    getPostDetail() {
      this.postTitle = this.$route.params.postTitle;
      this.postContent = this.$route.params.postContent;
      this.userId = this.$route.params.userId;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });

      this.postId = this.$route.params.postId;
      instance
        .get(
          `/post/search/post/1/${this.number}?postId=${
            this.postId
          }&userId=${window.localStorage.getItem("userId")}`
        )
        .then(res => {
          console.log(res);
          this.replyLists = res.data.list;
          this.totalPages = res.data.pages;
        });
    }
  },
  created() {
    this.getPostDetail();
  }
};
</script>

<style scoped></style>
