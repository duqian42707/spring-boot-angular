package com.dqv5.soccer.config;

import com.dqv5.soccer.management.table.SysLog;
import com.dqv5.soccer.management.service.SysLogService;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.utils.AspectUtil;
import com.dqv5.soccer.utils.IpIpFreeUtils;
import com.dqv5.soccer.utils.JsonUtil;
import com.dqv5.soccer.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {
    @Value("${soccer.system.log:true}")
    private boolean systemLog;
    @Resource
    private SysLogService sysLogService;

    public static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Pointcut("execution(* com.dqv5.soccer..*Controller.*(..))")
    public void allMethod() {
    }

    @Pointcut("!execution(* com.dqv5.soccer.*.TestController.*(..))")
    public void eliminateMethod() {
    }

    @Pointcut("allMethod() && eliminateMethod()")
    public void blendMethod() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before("blendMethod()")
    public void doBefore(JoinPoint joinPoint) {
        START_TIME.set(System.currentTimeMillis());
    }

    /**
     * 正常退出通知
     *
     * @param joinPoint
     */
    @AfterReturning("blendMethod()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //构建访问记录对象
        AuthUser currentUserDetail = Optional.ofNullable(SecurityUtils.getCurrentUserDetail()).orElse(new AuthUser(null, null, new ArrayList<>()));
        String userId = currentUserDetail.getUserId();
        String username = currentUserDetail.getUsername();
        String nickName = currentUserDetail.getNickName();
        String ip = AspectUtil.getIP(request);
        String address = IpIpFreeUtils.getAddressFromIp(ip);
        SysLog sl = SysLog.builder()
                .ip(ip)
                .address(address)
                .userId(userId)
                .username(username)
                .nickName(nickName)
                .className(signature.getDeclaringTypeName())
                .methodName(signature.getName())
                .args(AspectUtil.getArgs(request, joinPoint))
                .requestUrl(AspectUtil.getRequestUrl(request))
                .methodDesc(AspectUtil.getMethodDesc(joinPoint))
                .status(1)
                .requestType(request.getMethod())
                .errorInfo(null)
                .runTime(System.currentTimeMillis() - START_TIME.get())
                .build();
        if (systemLog) {
            sysLogService.insert(sl);
            log.debug("<<=====================正常访问通知,信息保存到数据库中=====================>>");
        } else {
            log.debug("{}==>访问记录==>{}", userId, JsonUtil.toString(sl));
        }
        START_TIME.remove();
    }

    /**
     * 异常出现通知
     *
     * @param joinPoint
     * @param ex
     * @throws NoSuchMethodException
     */
    @AfterThrowing(throwing = "ex", pointcut = "blendMethod()")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //构建访问记录对象
        AuthUser currentUserDetail = Optional.ofNullable(SecurityUtils.getCurrentUserDetail()).orElse(new AuthUser(null, null, new ArrayList<>()));
        String userId = currentUserDetail.getUserId();
        String username = currentUserDetail.getUsername();
        String nickName = currentUserDetail.getNickName();
        String ip = AspectUtil.getIP(request);
        String address = IpIpFreeUtils.getAddressFromIp(ip);
        SysLog sl = SysLog.builder()
                .ip(ip)
                .address(address)
                .userId(userId)
                .username(username)
                .nickName(nickName)
                .className(signature.getDeclaringTypeName())
                .methodName(signature.getName())
                .args(AspectUtil.getArgs(request, joinPoint))
                .requestUrl(AspectUtil.getRequestUrl(request))
                .methodDesc(AspectUtil.getMethodDesc(joinPoint))
                .status(0)
                .requestType(request.getMethod())
                .errorInfo(AspectUtil.getErrorInfo(ex))
                .runTime(System.currentTimeMillis() - START_TIME.get())
                .build();
        if (systemLog) {
            sysLogService.insert(sl);
            log.debug("<<=====================异常通知,信息保存到数据库中=====================>>");
        } else {
            log.debug("{}==>访问记录==>{}", userId, JsonUtil.toString(sl));
        }
        START_TIME.remove();
    }

    /**
     * 最终通知
     */
    @After("blendMethod()")
    public void doAfter() {
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("blendMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        Object object = pjp.proceed();
        return object;
    }
}
