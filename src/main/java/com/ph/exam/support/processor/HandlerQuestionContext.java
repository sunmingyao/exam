package com.ph.exam.support.processor;

import com.ph.exam.entity.BaseQuestion;
import com.ph.exam.support.exception.ExamException;
import com.ph.exam.service.handler.QuestionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题上下文
 *
 * @author : sunmingyao
 * @since : 2022/10/25 09:18
 */
@Component
public class HandlerQuestionContext {

    @Autowired
    private ApplicationContext applicationContext;

    public static Map<String, Class<QuestionHandler<? extends BaseQuestion>>> questionHandlerBeanMap = new HashMap<>();

    public QuestionHandler<? extends BaseQuestion> getQuestionHandler(String type) {
        Class<QuestionHandler<? extends BaseQuestion>> questionHandlerClass = questionHandlerBeanMap.get(type);
        if (questionHandlerClass == null) {
            throw new ExamException("没有:" + type + "对应的处理类型");
        }
        return applicationContext.getBean(questionHandlerClass);
    }
}
