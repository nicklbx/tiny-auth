package com.victor.auth.dao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * LocalMetaObjectHandler
 *
 * @Author nicklbx
 * @Date 2022/8/26 23:23
 */
@Slf4j
@Component
public class LocalMetaObjectHandler implements MetaObjectHandler {

    /**
     * insert时 自动填充规则
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        this.setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        this.strictInsertFill(metaObject, "deleteFlag", Integer.class, 0);
        this.setFieldValByName("isDelete",0,metaObject);

    }

    /**
     * update时 自动填充规则
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");

        this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
    }
}
