package com.ph.exam.controller;

import com.ph.exam.support.processor.HandlerQuestionContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunmingyao
 * @since 2023/11/2 14:04
 */

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private HandlerQuestionContext questionContext;
}
