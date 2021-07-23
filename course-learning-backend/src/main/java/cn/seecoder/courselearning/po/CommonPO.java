package cn.seecoder.courselearning.po;

import cn.seecoder.courselearning.annotation.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;

public class CommonPO {

    public boolean isFull() {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                NotNull notNull = field.getAnnotation(NotNull.class);
                field.setAccessible(true);
                if (field.get(this) == null && notNull != null) {
                    switch (notNull.type()) {
                        case ERROR:
                            return false;
                        case INT:
                            field.set(this, notNull.defaultInt());
                            break;
                        case STRING:
                            field.set(this,notNull.defaultString());
                            break;
                        case DOUBLE:
                            field.set(this,notNull.defaultDouble());
                            break;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }
}
