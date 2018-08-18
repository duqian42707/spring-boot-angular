package com.dqv5.soccer.config;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.security.AuthenticationException;
import com.dqv5.soccer.web.result.RestReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
//        model.addAttribute("author", "Magical Sam");
    }


    /**
     * 全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {CommonRuntimeException.class, AuthenticationException.class})
    @ResponseBody
    public ResponseEntity commonJsonExceptionHandler(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(), e);
        return RestReturn.fail(e.getMessage(), e);
    }

    /**
     * 全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity jsonExceptionHandler(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage(), e);
        return RestReturn.fail(e);
    }

}  