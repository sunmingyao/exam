package com.ph.exam.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.exam.entity.MultipleChoice;
import com.ph.exam.mapper.MultipleChoiceMapper;
import com.ph.exam.service.handler.QuestionHandler;
import com.ph.exam.support.annotation.QuestionType;
import org.springframework.stereotype.Service;

/**
 * @author sunmingyao
 * @since 2023/11/2 13:58
 */
@Service
@QuestionType(type = "multipleChoice")
public class MultipleChoiceService extends ServiceImpl<MultipleChoiceMapper, MultipleChoice> implements QuestionHandler<MultipleChoice> {

}
