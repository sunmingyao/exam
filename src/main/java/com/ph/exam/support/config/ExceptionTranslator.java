package com.ph.exam.support.config;

import com.baomidou.mybatisplus.extension.api.R;
import com.ph.exam.support.exception.ExamException;
import com.ph.exam.support.exception.NoSuchObjectException;
import com.ph.exam.support.exception.ObjectAlreadyExistsException;
import com.ph.exam.support.exception.ParamErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionTranslator<T> {


    /**
     * 响应吗500 内部错误
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R<T> onException(Exception e) {
        return handleError(e);
    }


    /**
     * 响应码 413 请求的参数错误,上传文件过大异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public R<T> onFileOutMaxException(Exception e) {
        return handleError(e);
    }

    /**
     * 响应码 400 请求的参数错误（或者不合理）
     */
    @ExceptionHandler({ParamErrorException.class, ExamException.class})
    @ResponseBody
    public R<T> onParamErrorException(Exception e) {
        return handleError(e);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public R<T> onHttpMessageNotReadableException() {
        return handleError("提交参数格式有误,请检查");
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public R<T> onMethodArgumentTypeMismatchException() {
        return handleError("方法参数类型不匹配");
    }


    /**
     * 不支持的方法 405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public R<T> onHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        return handleError(e);
    }

    /**
     * 校验参数格式合法性
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<T> bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errMsgStringBuilder = new StringBuilder("参数校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errMsgStringBuilder.append(String.format("%s.%n", fieldError.getDefaultMessage()));
        }
        return handleError(errMsgStringBuilder.toString());
    }

    /**
     * 对象已经存在，冲突错误
     */
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    @ResponseBody
    public R<T> onAllReadyExistsException(Exception e) {
        return handleError(e);
    }

    /**
     * 响应码 404 找不到
     *
     * @param e 异常
     * @return org.springframework.http.ResponseEntity<java.util.Map < java.lang.String, java.lang.Object>>
     * @author SunMingyao
     * @since 2020/9/22 16:53
     * @since JDK 11
     */
    @ExceptionHandler({NoSuchObjectException.class})
    @ResponseBody
    public R<T> onNotFoundException(Exception e) {
        return handleError(e);
    }

    /**
     * 封装异常信息显示实体.
     */
    private R<T> handleError(Throwable ex) {
        String message = ex.getMessage();
        return handleError(message);
    }

    private R<T> handleError(String message) {
        return R.failed(message);
    }

}
