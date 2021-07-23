package cn.seecoder.courselearning.po.course;

import cn.seecoder.courselearning.vo.course.CourseVO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Course {
    private Integer id;

    private String name;

    private String type;

    private String intro;

    private String picture;

    private String school;

    private Date createTime;

    private Date deleteTime;

    private Integer cost;

    private Integer teacherId;

    private String teacherName;

    // 课程点赞数
    private Integer likes;

    public Course() {
    }

    public Course(@NonNull CourseVO courseVO){
        this.id = courseVO.getId();
        this.name = courseVO.getName();
        this.type = courseVO.getType();
        this.intro = courseVO.getIntro();
        this.picture = courseVO.getPicture();
        this.school = courseVO.getSchool();
        this.createTime = courseVO.getCreateTime();
        this.cost = courseVO.getCost();
        this.teacherId = courseVO.getTeacherId();
        this.teacherName = courseVO.getTeacherName();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", intro='" + intro + '\'' +
                ", picture='" + picture + '\'' +
                ", school='" + school + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", cost=" + cost +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", likes=" + likes +
                '}';
    }
}