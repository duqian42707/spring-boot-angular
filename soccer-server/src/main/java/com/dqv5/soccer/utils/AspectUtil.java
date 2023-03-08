package com.dqv5.soccer.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AspectUtil {
    /**
     * 获取主机IP
     *
     * @return
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static boolean isValidIpv4(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return false;
        }
        if ("unkown".equalsIgnoreCase(ip) || ip.split("\\.").length != 4) {
            return false;
        }
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            return false;
        }
        return true;
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @param joinPoint
     * @return
     */
    public static String getArgs(HttpServletRequest request, JoinPoint joinPoint) {
        String method = request.getMethod();
        String args = "EMPTY";
        if (method.equals("GET") || method.equals("DELETE")) {
            args = setParamterArgs(request);
        } else if (method.equals("UPDATE")) {
            if ("application/json".equals(request.getContentType())) {
                Object[] _args = joinPoint.getArgs();
                for (Object arg : _args) {
                    if (arg != null) {
//                        args = JSON.toJSONString(arg, new SimplePropertyFilter());
                        args = JsonUtil.toString(arg);
                        break;
                    }
                }
            } else {
                args = setParamterArgs(request);
            }
        } else if ("POST".equals(method)) {
            if ("application/json".equals(request.getContentType())) {
                String pageInfo = (String) (request.getAttribute("pageInfo"));
                if (pageInfo == null) {
                    Object[] _args = joinPoint.getArgs();
                    for (Object arg : _args) {
                        if (arg != null) {
//                            args = JSON.toJSONString(arg, new SimplePropertyFilter());
                            args = JsonUtil.toString(arg);
                            break;
                        }
                    }
                } else {
                    args = pageInfo;
                }
            } else {
                args = setParamterArgs(request);
            }
        }
        return args;
    }

    /**
     * 在请求中读取Parameters，设置到args里
     *
     * @param request
     */
    public static String setParamterArgs(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String, String> parameterMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter, request.getParameter(parameter));
        }
        return JsonUtil.toString(parameterMap);
    }

    /**
     * 获取异常信息
     *
     * @param ex
     * @return
     */
    public static String getErrorInfo(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        ex.printStackTrace(pw);
        pw.flush();
        sw.flush();
        String s = sw.toString();
        String substring = s.substring(0, s.indexOf(")") + 1).replace("\n\t", "==>");
        return substring;
    }

    /**
     * 获取方法说明
     *
     * @param joinPoint
     * @return
     */
    public static String getMethodDesc(JoinPoint joinPoint) {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Class<?> targetClass = null;
//        Object[] argument = joinPoint.getArgs();
//        try {
//            targetClass = Class.forName(targetName);
//        } catch (ClassNotFoundException e) {
//            log.error("获取方法说明失败", e);
//        }
//        String apiOperationValue = "none";
//        if (targetClass != null) {
//            Method[] methods = targetClass.getMethods();
//            for (Method method : methods) {
//                if (method.getName().equals(methodName)) {
//                    Class<?>[] clazz = method.getParameterTypes();
//                    if (clazz.length == argument.length) {
//                        if (method.getAnnotation(ApiOperation.class) != null) {
//                            apiOperationValue = method.getAnnotation(ApiOperation.class).value();
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        return apiOperationValue;
        return "none";
    }

    /**
     * 获取请求路径
     *
     * @param request
     * @return
     */
    public static String getRequestUrl(HttpServletRequest request) {
        return request.getRequestURL().append(request.getQueryString() == null ? "" : "?" + request.getQueryString()).toString();
    }
}
