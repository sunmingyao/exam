package com.ph.exam.support.exception;

/**
 * 考试异常类
 *
 * @author : sunmingyao
 * @since : 2022/10/9 13:35
 */
public class ExamException extends RuntimeException{

    private static final long serialVersionUID = -5875371379845226068L;
    protected String msg;

    protected int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ExamException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public ExamException() {
        super();
    }

    public ExamException(String message) {
        super(message);
    }

    public ExamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExamException(Throwable cause) {
        super(cause);
    }

    protected ExamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
