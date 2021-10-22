# 课程学习网站-web项目
课程学习系统是为每一个渴求知识的人开发的学习网站，开发的目标是帮助学习的人更方便的学习自己想要的课程和技术。通过课程学习系统，期望帮助那些有知识需要的人少走弯路，能够高效率的学习并掌握自己所需要的知识。

## course-learning-backend项目说明

course-learning-backend是 courselearning 后端项目，使用了MySQL数据库。

本项目默认使用的 MySQL 用户为 `root` 密码为 `123456`
> 相关配置可参考文件 /src/main/resouces/application.yml `spring.datasource` 字段
> 请根据实际情况进行调整

项目部署前：
> 请使用 /sql/courselearning.sql 内的脚本初始化数据库和表
（可以直接打开MySQL命令行界面，在登录用户后，将 courselearning.sql 文件拷贝到命令行后按下回车）
>
> application.yml 文件中的 `web.file-upload-path` 字段为自定义的用于上传文件的目录路径，请根据实际情况进行调整
（建议部署前先创建目录专门用于存放上传的文件）

本地热部署：可以使用 IntelliJ IDEA 打开本项目，然后点击 Run 'CourseLearningApplication' 。

服务器部署：首先配置好服务器环境（JDK1.8 + MySQL），使用 maven 打包命令生成 jar 包，通过 shell 命令运行。








## course-learning-frontend项目结构说明

### /src/api
封装后端接口。本demo使用了axios库向后端服务发送HTTP请求，这部分的功能就是将调用后端controller提供的接口封装成易于前端页面使用的方法，供页面引入和使用。

### /src/components
公用的vue组件，用于在页面中引入和使用。

### /src/layouts
也是vue组件，与/src/components中的组件在本质上是相同的。不同之处在于，这部分的组件是和前端路由相关的，利用前端[嵌套路由](https://router.vuejs.org/zh/api/#router-view)来实现不同用户的页面使用不同的基础布局。

### /src/router
配置前端路由。
See[Vue Router](https://router.vuejs.org/)

### /src/views
实现每个前端页面，页面会引入需要的组件、api等，实现项目的UI、交互、前后端通信等。

### /src/App.vue
根组件。

### /src/main.js
入口文件，引入Vue框架与相关插件，定义Vue实例。

### /public/index.html
项目主页面，一般只定义一个空的根节点，在main.js里定义的Vue实例会挂在在这个根节点上。
