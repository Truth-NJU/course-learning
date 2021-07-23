import Vue from "vue";
import VueRouter from "vue-router";
// admin
import AdminLayout from "@/layouts/adminLayout.vue";
import CouponList from "@/views/admin/CouponList.vue";
import CouponCreate from "@/views/admin/CouponCreate.vue";
import CouponDeliver from "@/views/admin/CouponDeliver.vue";
// teacher
import TeacherLayout from "@/layouts/teacherLayout.vue";
import CourseList from "@/views/teacher/CourseList.vue";
import CourseCreate from "@/views/teacher/CourseCreate.vue";
import CourseEdit from "@/views/teacher/CourseEdit.vue";
import TeacherCenter from "@/views/teacher/UserCenter.vue";
// student
import StudentLayout from "@/layouts/studentLayout.vue";
import StudentCourseList from "@/views/student/CourseList.vue";
import UserCenter from "@/views/student/UserCenter.vue";
import HistoryOrder from "@/views/student/HistoryOrder.vue";
import CourseStudy from "@/views/student/CourseStudy.vue";
import CoursePeek from "@/views/student/CoursePeek.vue";
// post
import PostLayout from "../layouts/PostLayout";
import CoursePost from "../views/Post/CoursePost";
import PostCreate from "../views/Post/PostCreate";
import PostDetail from "../views/Post/PostDetail";
// default
import DefaultLayout from "@/layouts/defaultLayout.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import Home from "@/views/Home.vue";
// authentication
import { judgeTeacher, judgeStudent } from "@/util/auth";
import ReplyDetail from "../views/Post/ReplyDetail";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: DefaultLayout,
    children: [
      {
        path: "/",
        name: "Home",
        component: Home
      },
      {
        path: "login",
        name: "Login",
        component: Login
      },
      {
        path: "register",
        name: "Register",
        component: Register
      }
    ]
  },
  {
    path: "/post",
    name: "Post",
    component: PostLayout,
    children: [
      {
        path: "coursePost/:courseId",
        name: "CoursePost",
        component: CoursePost
      },
      {
        path: "create/:courseId",
        name: "PostCreate",
        component: PostCreate
      },
      {
        path: "postDetail/:postId/:postTitle/:postContent/:userId",
        name: "postDetail",
        component: PostDetail
      },
      {
        path: "replyDetail/:replyId/:replyContent/:replyUserId",
        name: "replyDetail",
        component: ReplyDetail
      }
    ]
  },
  {
    path: "/admin",
    name: "Admin",
    component: AdminLayout,
    children: [
      {
        path: "/",
        name: "CouponList",
        redirect: "coupon"
      },
      {
        path: "coupon",
        name: "CouponList",
        exact: true,
        component: CouponList
      },
      {
        path: "coupon/create",
        name: "CouponCreate",
        component: CouponCreate
      },
      {
        path: "coupon/deliver",
        name: "CouponDeliver",
        component: CouponDeliver
      }
    ]
  },
  {
    path: "/teacher",
    name: "Teacher",
    component: TeacherLayout,
    children: [
      {
        path: "/",
        name: "TeacherCourseList",
        component: CourseList
      },
      {
        path: "user/:userId",
        name: "TeacherCenter",
        component: TeacherCenter
      },
      {
        path: "create",
        name: "TeacherCourseCreate",
        component: CourseCreate
      },
      {
        path: "edit/:courseId",
        name: "TeacherCourseEdit",
        component: CourseEdit
      }
    ]
  },
  {
    path: "/student",
    name: "Student",
    component: StudentLayout,
    children: [
      {
        path: "/",
        name: "StudentCourseList",
        component: StudentCourseList
      },
      {
        path: "user/:userId",
        name: "StudentUserCenter",
        component: UserCenter
      },
      {
        path: "history",
        name: "HistoryOrder",
        component: HistoryOrder
      },
      {
        path: "course/:courseId",
        name: "CourseStudy",
        component: CourseStudy
      },
      {
        path: "peek/:courseId",
        name: "CoursePeek",
        component: CoursePeek
      }
    ]
  }
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  let legal = false;
  if (!to.path.startsWith("/student") && !to.path.startsWith("/teacher"))
    legal = true;
  if (to.path.startsWith("/student") && judgeStudent()) legal = true;
  if (to.path.startsWith("/teacher") && judgeTeacher()) legal = true;
  if (to.name === "CoursePeek") legal = true;
  if (legal) {
    next();
  } else {
    next({ name: "Login" });
  }
});

export default router;
