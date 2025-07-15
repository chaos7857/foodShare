package com.cc.backend.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.model.dto.common.PageRequest;
import com.cc.backend.model.dto.user.LoginRequest;
import com.cc.backend.model.dto.user.RegisterRequest;
import com.cc.backend.model.dto.user.UserAddRequest;
import com.cc.backend.model.entity.User;
import com.cc.backend.model.vo.LoginUserVO;
import com.cc.backend.service.UserService;
import com.cc.backend.utils.ResultUtils;
import com.cc.backend.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    /*用户模块*/

    @Resource
    private UserService userService;

    /*
    * 注册*/
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody RegisterRequest registerRequest) {
        ThrowUtils.throwIf(registerRequest==null, ErrorCode.PARAMS_ERROR);
        String userAccount = registerRequest.getUserAccount();
        String userPassword = registerRequest.getUserPassword();
        String passwordConfirm = registerRequest.getPasswordConfirm();
        Long result = userService.userRegister(userAccount, userPassword, passwordConfirm);
        return ResultUtils.success(result);
    }

    /*
    * 登录*/
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(loginRequest==null, ErrorCode.PARAMS_ERROR);
        log.info(loginRequest.toString());
        String userAccount = loginRequest.getUserAccount();
        String userPassword = loginRequest.getPassword();
        User user = userService.userLogin(userAccount, userPassword, request);
        LoginUserVO loginUserVO = userService.getLoginUserVO(user);
        return ResultUtils.success(loginUserVO);
    }

    /*
    * 个人信息查询*/
    @GetMapping("/me")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest httpServletRequest){
        User user = userService.getLoginUser(httpServletRequest);
        LoginUserVO loginUserVO = userService.getLoginUserVO(user);
        return ResultUtils.success(loginUserVO);
    }

    /*
    * 个人信息修改*/

    /*
    * 退出登录*/
    @GetMapping("/logout")
    public BaseResponse<String> userLogout(HttpServletRequest httpServletRequest){
        String result = userService.userLogout(httpServletRequest);
        return ResultUtils.success(result);
    }

    /// /////////////////////////////////////////
    ///             管理员权限                 ///
    /// ////////////////////////////////////////

    /*
    * 添加用户*/
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest user) {
        ThrowUtils.throwIf(BeanUtil.isEmpty(user), ErrorCode.PARAMS_ERROR);
        String userAccount = user.getUserAccount();
        String userName = user.getUserName();
        Long l = userService.addUser(userAccount, userName);
        return ResultUtils.success(l);
    }

    /*
     * 删除用户*/

    /*
     * 更新用户信息*/

    /*
    * 查看用户*/
    @GetMapping("/get")
    public BaseResponse<LoginUserVO> getUser(@RequestParam Long id) {
        ThrowUtils.throwIf(id<0, ErrorCode.PARAMS_ERROR,"id不为负");
        User user = userService.getById(id);
        LoginUserVO userVO = BeanUtil.copyProperties(user, LoginUserVO.class);
        ThrowUtils.throwIf(userVO ==null,ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userVO);
    }

    // 以下批量操作---------------------------------------------------------------------

    /*
    * 增*/

    /*
    * 删*/

    /*
    * 改*/

    /*
    * 查
    * TODO(cc):条件查询还没有加哦哦哦*/
    @PostMapping("/get/pager")
    public BaseResponse<Page<LoginUserVO>> getUserPager(@RequestBody PageRequest pageRequest) {
        ThrowUtils.throwIf(pageRequest==null,ErrorCode.PARAMS_ERROR);
        int current = pageRequest.getCurrent();
        int pageSize = pageRequest.getPageSize();

//          条件查询，先不加
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
//        queryWrapper.eq(StrUtil.isNotBlank(userRole), "userRole", userRole);
//        queryWrapper.like(StrUtil.isNotBlank(userAccount), "userAccount", userAccount);
//        queryWrapper.like(StrUtil.isNotBlank(userName), "userName", userName);
//        queryWrapper.like(StrUtil.isNotBlank(userProfile), "userProfile", userProfile);
//        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);

        Page<User> userPage = userService.page(new Page<>(current, pageSize));
        List<LoginUserVO> userVOList = userPage.getRecords().stream()
                .map(userService::getLoginUserVO)
                .collect(Collectors.toList());

        Page<LoginUserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotal());
        userVOPage.setRecords(userVOList);

        return ResultUtils.success(userVOPage);
    }
}
