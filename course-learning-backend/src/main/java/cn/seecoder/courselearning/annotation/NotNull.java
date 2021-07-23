package cn.seecoder.courselearning.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

    int defaultInt() default 0;

    String defaultString() default "";

    double defaultDouble() default 0.0;

    Type type() default Type.ERROR;

    public enum Type{
        ERROR,INT,STRING,DOUBLE
    }
}
