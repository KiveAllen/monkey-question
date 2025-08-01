package com.allen.monkeyQuestion.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.allen.monkeyQuestion.common.BaseResponse;
import com.allen.monkeyQuestion.common.DeleteRequest;
import com.allen.monkeyQuestion.common.ErrorCode;
import com.allen.monkeyQuestion.common.ResultUtils;
import com.allen.monkeyQuestion.constant.UserConstant;
import com.allen.monkeyQuestion.exception.ThrowUtils;
import com.allen.monkeyQuestion.model.dto.questionBank.QuestionBankAddRequest;
import com.allen.monkeyQuestion.model.dto.questionBank.QuestionBankQueryRequest;
import com.allen.monkeyQuestion.model.dto.questionBank.QuestionBankUpdateRequest;
import com.allen.monkeyQuestion.model.entity.QuestionBank;
import com.allen.monkeyQuestion.model.entity.User;
import com.allen.monkeyQuestion.model.vo.QuestionBankVO;
import com.allen.monkeyQuestion.service.QuestionBankService;
import com.allen.monkeyQuestion.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.platform.hotkey.client.callback.JdHotKeyStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题库接口
 */
@RestController
@RequestMapping("/questionBank")
@Slf4j
public class QuestionBankController {

    @Resource
    private QuestionBankService questionBankService;

    @Resource
    private UserService userService;

    // region 增删改查 (管理员)

    /**
     * 创建题库（仅管理员可用）
     *
     * @param questionBankAddRequest 创建题库参数
     * @param request                http请求
     * @return 创建成功
     */
    @PostMapping("/add")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addQuestionBank(@RequestBody QuestionBankAddRequest questionBankAddRequest,
                                              HttpServletRequest request) {
        ThrowUtils.throwIf(questionBankAddRequest == null, ErrorCode.PARAMS_ERROR);

        QuestionBank questionBank = new QuestionBank();
        BeanUtils.copyProperties(questionBankAddRequest, questionBank);

        User loginUser = userService.getLoginUser(request);
        questionBank.setUserId(loginUser.getId());

        return ResultUtils.success(questionBankService.addQuestionBank(questionBank));
    }

    /**
     * 删除题库（仅管理员可用）
     *
     * @param deleteRequest 删除题库的id
     * @return 删除成功
     */
    @DeleteMapping("/delete")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteQuestionBank(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(questionBankService.deleteQuestionBank(deleteRequest.getId()));
    }

    /**
     * 更新题库（仅管理员可用）
     *
     * @param questionBankUpdateRequest 更新题库参数
     * @return 更新成功
     */
    @PostMapping("/update")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateQuestionBank(@RequestBody QuestionBankUpdateRequest questionBankUpdateRequest) {
        ThrowUtils.throwIf(questionBankUpdateRequest == null
                || questionBankUpdateRequest.getId() <= 0, ErrorCode.PARAMS_ERROR);

        QuestionBank questionBank = new QuestionBank();
        BeanUtils.copyProperties(questionBankUpdateRequest, questionBank);

        return ResultUtils.success(questionBankService.updateQuestionBank(questionBank));
    }

    /**
     * 分页获取题库列表（仅管理员可用）
     *
     * @param questionBankQueryRequest 查询参数
     * @return 分页列表
     */
    @PostMapping("/list/page")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionBank>> listQuestionBankByPage(@RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        ThrowUtils.throwIf(questionBankQueryRequest == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(questionBankService.listQuestionBankByPage(questionBankQueryRequest));
    }

    /**
     * 根据 id 获取题库（仅管理员可用）
     *
     * @param id 题库id
     * @return 封装类
     */
    @GetMapping("/get")
    public BaseResponse<QuestionBank> getQuestionBankById(@RequestParam Long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 获取封装类
        return ResultUtils.success(questionBankService.getQuestionBank(id));
    }

    // endregion

    /**
     * 根据 id 获取题库下题目（封装类）
     *
     * @param questionBankQueryRequest 查询参数
     * @return 封装类
     */
    @PostMapping("/get/vo")
    public BaseResponse<QuestionBankVO> getQuestionBankVOById(@RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        Long id = questionBankQueryRequest.getId();
        Boolean needQueryQuestionList = questionBankQueryRequest.getNeedQueryQuestionList();
        int current = questionBankQueryRequest.getCurrent();
        int pageSize = questionBankQueryRequest.getPageSize();
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);

        // hotkey
        String key = "bank_detail_" + id;
        // 是热key的情况
        if (JdHotKeyStore.isHotKey(key)) {
            // 从本地缓存获取
            Object cachedQuestionBankVO = JdHotKeyStore.get(key);
            if (cachedQuestionBankVO != null) {
                return ResultUtils.success((QuestionBankVO) cachedQuestionBankVO);
            }
        }

        // 获取封装类
        QuestionBankVO questionBankVO = questionBankService.getQuestionBankVO(id, needQueryQuestionList, current, pageSize);

        // 设置本地缓存
        JdHotKeyStore.smartSet(key, questionBankVO);

        return ResultUtils.success(questionBankVO);
    }

    /**
     * 分页获取题库列表（封装类）
     *
     * @param questionBankQueryRequest 查询参数
     * @return 分页列表
     */
    @PostMapping("/list/page/vo")
    @SentinelResource(value = "listQuestionBankVOByPage",
            blockHandler = "handleBlockException",
            fallback = "handleFallback")
    public BaseResponse<Page<QuestionBankVO>> listQuestionBankVOByPage(@RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        long size = questionBankQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 200, ErrorCode.PARAMS_ERROR);
        // 获取封装类
        return ResultUtils.success(questionBankService.getQuestionBankVOPage(questionBankQueryRequest));
    }

    /**
     * listQuestionBankVOByPage 流控操作
     * 限流：提示“系统压力过大，请耐心等待”
     * 熔断：执行降级操作
     */
    public BaseResponse<Page<QuestionBankVO>> handleBlockException(@RequestBody QuestionBankQueryRequest questionBankQueryRequest,
                                                                   BlockException ex) {
        // 降级操作
        if (ex instanceof DegradeException) {
            return handleFallback(questionBankQueryRequest, ex);
        }
        // 限流操作
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统压力过大，请耐心等待");
    }


    /**
     * listQuestionBankVOByPage 降级操作：直接返回本地数据
     */
    public BaseResponse<Page<QuestionBankVO>> handleFallback(@RequestBody QuestionBankQueryRequest questionBankQueryRequest,
                                                             Throwable ex) {
        // 可以返回本地数据或空数据
        return ResultUtils.success(null);
    }



}
