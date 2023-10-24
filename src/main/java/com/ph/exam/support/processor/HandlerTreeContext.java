package com.ph.exam.support.processor;

import com.ph.exam.support.exception.ExamException;
import com.ph.exam.service.handler.TreeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 树状处理器上下文
 * @author : sunmingyao
 */

@Component
public class HandlerTreeContext {

    @Autowired
    private ApplicationContext applicationContext;

    public static Map<String, Class<TreeHandler>> treeHandlerBeanMap = new HashMap<>();

    public TreeHandler getTreeHandler(String type) {
        Class<TreeHandler> treeHandlerClass = treeHandlerBeanMap.get(type);
        if (treeHandlerClass == null) {
            throw new ExamException("没有:" + type + "对应的处理类型");
        }
        return applicationContext.getBean(treeHandlerClass);
    }
}
