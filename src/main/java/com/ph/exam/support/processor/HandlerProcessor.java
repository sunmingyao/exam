package com.ph.exam.support.processor;

import com.ph.exam.entity.BaseQuestion;
import com.ph.exam.support.annotation.QuestionType;
import com.ph.exam.support.annotation.TreeHandlerType;
import com.ph.exam.service.handler.QuestionHandler;
import com.ph.exam.service.handler.TreeHandler;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * 容器增强处理
 * 注解类型的的类放入到bean中
 *
 * @author : sunmingyao
 * @since : 2022/10/20 17:35
 */

@Component
public class HandlerProcessor implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //树状处理器
        Map<String, Object> treeStrategyMap = applicationContext.getBeansWithAnnotation(TreeHandlerType.class);
        treeStrategyMap.forEach((k, v) -> {
            Class<TreeHandler> treeHandlerClass = (Class<TreeHandler>) v.getClass();
            String type;
            if (AopUtils.isAopProxy(v)) {
                type = Objects.requireNonNull(AnnotationUtils.findAnnotation(v.getClass(), TreeHandlerType.class)).type();

            } else {
                type = treeHandlerClass.getAnnotation(TreeHandlerType.class).type();
            }
            HandlerTreeContext.treeHandlerBeanMap.put(type, treeHandlerClass);
        });

        //问题处理器
        Map<String, Object> questionHandlerBeanMap = applicationContext.getBeansWithAnnotation(QuestionType.class);
        questionHandlerBeanMap.forEach((k, v) -> {

            Class<QuestionHandler<? extends BaseQuestion>> planClass = (Class<QuestionHandler<? extends BaseQuestion>>) v.getClass();

            String type;
            if (AopUtils.isAopProxy(v)) {
                type = Objects.requireNonNull(AnnotationUtils.findAnnotation(v.getClass(), QuestionType.class)).type();

            } else {
                type = planClass.getAnnotation(QuestionType.class).type();
            }
            HandlerQuestionContext.questionHandlerBeanMap.put(type, planClass);
        });
    }
}
