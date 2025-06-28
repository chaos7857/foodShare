package com.cc.backend.controller;

import cn.hutool.json.JSONUtil;
import com.cc.backend.annotation.RequireRole;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.dto.common.BaseResponse;
import com.cc.backend.model.dto.review.AddReviewRequest;
import com.cc.backend.model.entity.Review;
import com.cc.backend.model.entity.Share;
import com.cc.backend.model.entity.User;
import com.cc.backend.service.ReviewService;
import com.cc.backend.service.ShareService;
import com.cc.backend.utils.ResultUtils;
import com.cc.backend.utils.ThrowUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
public class ReviewController {
    /*审核模块*/

    @Resource
    private ReviewService reviewService;

    @Resource
    private ShareService shareService;

    /// /////////////////////////////////////////
    ///             管理员权限                 ///
    /// ////////////////////////////////////////

    /*
    * 对未审核数据进行审核*/
    @PostMapping("/add")
    @RequireRole(userRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addReview(@RequestBody AddReviewRequest addReviewRequest,
                                        HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER);
        Long userId = user.getId();
        Long shareId = addReviewRequest.getShareId();
        Share share = shareService.getById(shareId);

        ThrowUtils.throwIf(share == null, ErrorCode.PARAMS_ERROR);

        Review review = new Review();
        BeanUtils.copyProperties(addReviewRequest, review);
        review.setUserId(userId);
        reviewService.save(review);

        Long reviewId = review.getId();
        Integer reviewStatus = review.getReviewStatus();

        String reviewId1 = share.getReviewId();
        List<String> list = JSONUtil.toList(reviewId1, String.class);
        list.add(String.valueOf(reviewId));
        share.setReviewId(JSONUtil.toJsonStr(list));
        share.setLastReviewStatus(reviewStatus);
        shareService.updateById(share);

        return ResultUtils.success(reviewId);
    }

    /*
    * 删除审核记录（这个不太合理）*/


    /*
    * 更新审核（这里通过新增一条审核来更新share的审核状态）*/


    /*
    * 查询所有未审核*/



    /*
    * 查询某个share的所有审核信息*/
    @RequireRole
    @PostMapping("/page/all")
    public BaseResponse<List<Review>> listAllReview(@RequestParam Long shareId){
        Share share = shareService.getById(shareId);
        String reviewIdStr = share.getReviewId();
        // TODO(CC):优化点，修改为分页查询
        List<Review> collect = JSONUtil.toList(reviewIdStr, Long.class).stream()
                .map(reviewId -> reviewService.getById(reviewId))
                .collect(Collectors.toList());
        return ResultUtils.success(collect);
    }



    /// /////////////////////////////////////////
    ///             开发者权限                 ///
    /// ////////////////////////////////////////
}
