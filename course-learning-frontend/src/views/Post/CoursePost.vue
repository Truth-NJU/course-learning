<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-row>
        <v-btn
          class="ma-4 mb-8"
          fab
          dark
          color="indigo"
          @click="jumpToCreatePost"
        >
          <v-icon dark large>
            mdi-plus
          </v-icon>
        </v-btn>
      </v-row>
      <li v-for="(post, key, index) in postLists" :key="index">
        <v-card elevation="1">
          <v-card-title>{{ post.postTitle }}</v-card-title>
          <v-card-text>{{post.postContent}}</v-card-text>
          <v-card-actions @click="jumpToDetail(post)">查看详情</v-card-actions>
        </v-card>
      </li>
      <v-pagination
        v-model="pageNo"
        :length="totalPages"
        @next="getNextPostLists"
        @previous="getPreviousPostLists"
      >
      </v-pagination>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "CoursePost",
  data() {
    return {
      pageNo: 1,
      number: 4,
      postLists: [],
      totalPages: 0
    };
  },
  methods: {
    jumpToCreatePost() {
      const { courseId } = this.$route.params;
      this.$router.push(`/post/create/${courseId}`);
    },
    jumpToDetail(post){
      this.$router.push(`/post/postDetail/${post.postId}/${post.postTitle}/${post.postContent}/${post.userId}`);
    },
    getNextPostLists() {
      this.pageNo++;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      const { courseId } = this.$route.params;
      instance
        .get(`/post/search/course/${courseId}/${this.pageNo}/${this.number}`)
        .then(res => {
          console.log(res.data.list);
          this.postLists = res.data.list;
        });
    },
    getPreviousPostLists() {
      this.pageNo--;
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      const { courseId } = this.$route.params;
      instance
        .get(`/post/search/course/${courseId}/${this.pageNo}/${this.number}`)
        .then(res => {
          console.log(res.data.list);
          this.postLists = res.data.list;
        });
    },
    getPostLists() {
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      const { courseId } = this.$route.params;
      instance
        .get(`/post/search/course/${courseId}/1/${this.number}`)
        .then(res => {
          console.log(res.data);
          this.postLists = res.data.list;
          this.totalPages = res.data.pages;
          console.log(this.postLists);
        });
    }
  },
  created() {
    this.getPostLists();
  }
};
</script>

<style scoped></style>
