package com.cc.backend.aop;

import com.cc.backend.annotation.RequireRole;
import com.cc.backend.constant.UserConstant;
import com.cc.backend.exception.BusinessException;
import com.cc.backend.exception.ErrorCode;
import com.cc.backend.model.entity.User;
import com.cc.backend.utils.ThrowUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthInterceptor {

    @Around("@annotation(requireRole)")
    public Object authInterception(ProceedingJoinPoint joinPoint, RequireRole requireRole) throws Throwable {
        // 获取request，没有就截断
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Object attribute = request.getSession().getAttribute(UserConstant.LOGIN_USER);
        ThrowUtils.throwIf(
                attribute == null, ErrorCode.NOT_LOGIN_ERROR
        );
        // 如果没指定就放行
        String mustRole = requireRole.userRole();
        if ("".equals(mustRole)) {
            return joinPoint.proceed();
        }
        // 否则就看当前登录的权限
        User user = (User) attribute;
        String userRole = user.getUserRole();
        // 如果是dev，放行
        if (UserConstant.DEV_ROLE.equals(userRole)) {
            return joinPoint.proceed();
        }
        // 如果是admin，需要参数是admin或者其他才能放行
        if (UserConstant.ADMIN_ROLE.equals(userRole) && UserConstant.ADMIN_ROLE.equals(mustRole)) {
            return joinPoint.proceed();
        }
        // 如果是其他的，需要是对应权限放行，当然因为暂时没有，所以如果出现其他权限，大概率是我打错字了，截断报错吧
//        if (mustRole.equals(userRole)) {
//            return joinPoint.proceed();
//        }

        // 否则就截断
        throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
    }

}