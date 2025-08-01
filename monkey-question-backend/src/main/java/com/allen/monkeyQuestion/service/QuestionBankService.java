package com.allen.monkeyQuestion.service;

import com.allen.monkeyQuestion.model.dto.questionBank.QuestionBankQueryRequest;
import com.allen.monkeyQuestion.model.entity.QuestionBank;
import com.allen.monkeyQuestion.model.vo.QuestionBankVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 题库服务
 *
 */
public interface QuestionBankService extends IService<QuestionBank> {

    /**
     * 添加题库
     * @param questionBank 添加的题库
     * @return 添加的题库id
     */
    Long addQuestionBank(QuestionBank questionBank);

    /**
     * 删除题库
     * @param id 题库id
     * @return 是否删除成功
     */
    Boolean deleteQuestionBank(long id);

    /**
     * 更新题库
     * @param questionBank 更新的题库
     * @return 是否更新成功
     */
    Boolean updateQuestionBank(QuestionBank questionBank);

    /**
     * 获取题库
     *
     * @param id 题库id
     * @return 题库类
     */
    QuestionBank getQuestionBank(Long id);

    /**
     * 分页查询题库
     * @param questionBankQueryRequest 查询条件
     * @return 分页结果
     */
    Page<QuestionBank> listQuestionBankByPage(QuestionBankQueryRequest questionBankQueryRequest);

    /**
     * 获取题库封装
     *
     * @param id 题库id
     * @param needQueryQuestionList 是否需要查询题目列表
     * @param current 当前页
     * @param pageSize 每页大小
     * @return 封装类
     */
    QuestionBankVO getQuestionBankVO(Long id ,Boolean needQueryQuestionList, int current, int pageSize);

    /**
     * 分页获取题库封装
     *
     * @param questionBankQueryRequest 分页查询参数
     * @return 分页结果
     */
    Page<QuestionBankVO> getQuestionBankVOPage(QuestionBankQueryRequest questionBankQueryRequest);

}

