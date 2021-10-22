# 课程学习网站-web项目
​ 课程学习系统是为每一个渴求知识的人开发的学习网站，开发的目标是帮助学习的人更方便的学习自己想要的课程和技术。通过课程学习系统，期望帮助那些有知识需要的人少走弯路，能够高效率的学习并掌握自己所需要的知识。

## 项目说明

courselearning-backend是 courselearning 后端项目，使用了MySQL数据库。

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
