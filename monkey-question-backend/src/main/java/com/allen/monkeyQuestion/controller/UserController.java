package com.allen.monkeyQuestion.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.allen.monkeyQuestion.common.BaseResponse;
import com.allen.monkeyQuestion.common.DeleteRequest;
import com.allen.monkeyQuestion.common.ErrorCode;
import com.allen.monkeyQuestion.common.ResultUtils;
import com.allen.monkeyQuestion.constant.UserConstant;
import com.allen.monkeyQuestion.exception.BusinessException;
import com.allen.monkeyQuestion.exception.ThrowUtils;
import com.allen.monkeyQuestion.model.dto.user.*;
import com.allen.monkeyQuestion.model.entity.User;
import com.allen.monkeyQuestion.model.vo.LoginUserVO;
import com.allen.monkeyQuestion.model.vo.UserVO;
import com.allen.monkeyQuestion.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口
 * 提供了用户相关的所有操作，包括但不限于用户的注册、登录、注销以及用户的增删改查等。
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页获取用户封装列表
     *
     * @param userQueryRequest 查询条件
     * @return 分页后的用户视图列表
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        List<UserVO> userVO = userService.getUserVOByUserList(userPage.getRecords());
        userVOPage.setRecords(userVO);
        return ResultUtils.success(userVOPage);
    }

    /**
     * 根据 ID 获取包装类
     *
     * @param id 用户ID
     * @return 用户视图对象
     */
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id) {
        BaseResponse<User> response = getUserById(id);
        User user = response.getData();
        return ResultUtils.success(userService.getUserVOByUserList(user));
    }

    /**
     * 更新个人信息
     *
     * @param userUpdateMyRequest 包含更新信息的对象
     * @param request             HTTP请求对象
     * @return 更新结果
     */
    @PostMapping("/update/my")
    public BaseResponse<Boolean> updateMyUser(@RequestBody UserUpdateMyRequest userUpdateMyRequest,
                                              HttpServletRequest request) {
        ThrowUtils.throwIf(userUpdateMyRequest == null, ErrorCode.PARAMS_ERROR);

        User loginUser = userService.getLoginUser(request);
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);

        user.setId(loginUser.getId());
        boolean result = userService.updateById(user);

        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 签到操作
     *
     * @param request HTTP请求对象
     * @return 当前是否已签到成果
     */
    @PostMapping("/add/sign_in")
    public BaseResponse<Boolean> addUserSignIn(HttpServletRequest request) {
        // 必须要登录才能签到
        User loginUser = userService.getLoginUser(request);
        boolean result = userService.addUserSingIn(loginUser.getId());
        return ResultUtils.success(result);
    }

    /**
     * 获取用户签到记录
     *
     * @param year    年份（为空表示当前年份）
     * @param request 登录态
     * @return 签到记录映射
     */
    @GetMapping("/get/sign_in")
    public BaseResponse<List<Integer>> getUserSignInRecord(Integer year, HttpServletRequest request) {
        // 必须要登录才能获取
        User loginUser = userService.getLoginUser(request);
        List<Integer> userSignInRecord = userService.getUserSignInRecord(loginUser.getId(), year);
        return ResultUtils.success(userSignInRecord);
    }


    // region 管理员增删改查

    /**
     * 创建用户
     *
     * @param userAddRequest 新用户信息
     * @return 新创建的用户ID
     */
    @PostMapping("/add")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        // 校验参数是否为空
        ThrowUtils.throwIf(userAddRequest == null, ErrorCode.PARAMS_ERROR);

        // 转为User
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);

        // 调用方法
        Long userId = userService.addUser(user);
        return ResultUtils.success(userId);
    }

    /**
     * 删除用户
     *
     * @param deleteRequest 包含待删除用户ID的对象
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.deleteUser(deleteRequest.getId()));
    }

    /**
     * 更新用户
     *
     * @param userUpdateRequest 包含更新用户信息的对象
     * @return 更新结果
     */
    @PostMapping("/update")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        ThrowUtils.throwIf(userUpdateRequest == null
                           || userUpdateRequest.getId() == null, ErrorCode.PARAMS_ERROR);
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        return ResultUtils.success(userService.updateUser(user));
    }

    /**
     * 根据 ID 获取用户（仅管理员）
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/get")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<User> getUserById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(user);
    }

    /**
     * 分页获取用户列表（仅管理员）
     *
     * @param userQueryRequest 查询条件
     * @return 分页后的用户列表
     */
    @PostMapping("/list/page")
    //    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<User>> listUserByPage(@RequestBody UserQueryRequest userQueryRequest) {
        return ResultUtils.success(userService.listUserByPage(userQueryRequest));
    }

    // endregion

    // region 登录相关

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求对象，包含用户名、密码和确认密码
     * @return 注册成功后返回新用户的ID
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 校验请求是否为空
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);

        long result = userService.userRegister(
                userRegisterRequest.getUserAccount(),
                userRegisterRequest.getUserPassword(),
                userRegisterRequest.getCheckPassword());

        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求对象，包含用户名和密码
     * @param request          HTTP请求对象
     * @return 登录成功后的用户信息
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        // 校验请求是否为空
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);

        // 登录校验
        LoginUserVO loginUserVO = userService.userLogin(
                userLoginRequest.getUserAccount(),
                userLoginRequest.getUserPassword(),
                request);

        return ResultUtils.success(loginUserVO);
    }

    /**
     * 用户注销
     *
     * @param request HTTP请求对象
     * @return 注销结果
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        // 校验
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);

        return ResultUtils.success(userService.userLogout(request));
    }

    /**
     * 获取当前登录用户
     *
     * @param request HTTP请求对象
     * @return 当前登录用户的信息
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {

        User user = userService.getLoginUser(request);

        return ResultUtils.success(userService.getLoginUserVO(user));
    }

    // endregion

}
