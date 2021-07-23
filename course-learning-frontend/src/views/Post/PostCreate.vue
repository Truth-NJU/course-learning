<template>
  <div>
    <v-container class="pl-16 pr-16">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-text-field v-model="postInfo.title" label="帖子标题"></v-text-field>
        <v-textarea v-model="postInfo.content" label="帖子内容"></v-textarea>

        <v-btn class="ml-0 mt-8 info" @click="submit">
          确认
        </v-btn>
      </form>
    </v-container>

    <!-- 提示对话框 -->
    <v-dialog v-model="dialog" width="500">
      <v-card>
        <v-card-title> </v-card-title>

        <v-card-text>
          {{ msg }}
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="jumpback">
            确认
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "PostCreate",

  data() {
    return {
      postInfo: {
        title: "",
        content: ""
      },
      dialog: false,
      msg: ""
    };
  },

  methods: {
    submit() {
      let instance = axios.create({
        baseURL: "http://localhost:8081/api",
        timeout: 1000 // 超时时长
      });
      const { courseId } = this.$route.params;
      const post = {
        postId: 100,
        userId: window.localStorage.getItem("userId"),
        courseId: courseId,
        title: this.postInfo.title,
        postTime: new Date(),
        content: this.postInfo.content
      };
      instance.post("/post/create", post).then(res => {
        console.log(res);
        if (res.status === 200) {
          this.msg = "帖子发布成功";
          this.dialog = true;
        } else {
          this.msg = "帖子发布失败";
          this.dialog = true;
        }
      });
    },
    jumpback() {
      this.dialog = false;
      const { courseId } = this.$route.params;
      this.$router.push(`/post/coursePost/${courseId}`);
    }
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
</style>
