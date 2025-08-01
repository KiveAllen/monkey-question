package com.allen.monkeyQuestion.model.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 创建题库题目请求
 */
@Data
public class QuestionBankQuestionAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 题库id
     */
    private Long questionBankId;
    /**
     * 题目id
     */
    private Long questionId;
}