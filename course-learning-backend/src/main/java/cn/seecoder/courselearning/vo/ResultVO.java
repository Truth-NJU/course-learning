package cn.seecoder.courselearning.vo;

import cn.seecoder.courselearning.enums.HttpStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg, @NonNull T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(@NonNull T data) {
        HttpStatus httpStatus = HttpStatus.COMMON_OK;
        this.code = httpStatus.getCode();
        this.msg = httpStatus.getDescription();
        this.data = data;
    }

    public ResultVO() {
    }

    public static ResultVO fail(HttpStatus httpStatus) {
        ResultVO resultVO = new ResultVO();
        resultVO.code = httpStatus.getCode();
        resultVO.msg = httpStatus.getDescription();
        return resultVO;
    }

    public static ResultVO succeed() {
        ResultVO resultVO = new ResultVO();
        HttpStatus httpStatus = HttpStatus.COMMON_OK;
        resultVO.code = httpStatus.getCode();
        resultVO.msg = httpStatus.getDescription();
        return resultVO;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                (null == data ? "" : (",data='" + data.toString())) +
                '}';
    }
}
