package com.dqv5.soccer.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充：创建人、创建时间、更新人、更新时间
 * https://baomidou.com/pages/4c6bcf/
 */
@Slf4j
@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            //根据属性的名字加入判断即可
            if (metaObject.hasGetter("createdDate")) {
                this.setFieldValByName("createdDate", new Date(), metaObject);
            }
            if (metaObject.hasGetter("createdBy")) {
                this.setFieldValByName("createdBy", getCurrentUserId(), metaObject);
            }
        } catch (Exception e) {
            throw new CommonRuntimeException("自动注入异常 => " + e.getMessage());
        }
        updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (metaObject.hasGetter("lastModifiedDate")) {
                this.setFieldValByName("lastModifiedDate", new Date(), metaObject);
            }
            if (metaObject.hasGetter("lastModifiedBy")) {
                this.setFieldValByName("lastModifiedBy", getCurrentUserId(), metaObject);
            }
        } catch (Exception e) {
            throw new CommonRuntimeException("自动注入异常 => " + e.getMessage());
        }
    }

    /**
     * 获取登录用户名
     */
    private String getCurrentUserId() {
        try {
            return SecurityUtils.getCurrentUserId();
        } catch (Exception e) {
            return null;
        }
    }

}
