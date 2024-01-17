package com.dqv5.soccer.service;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.table.SysConfigTable;

import java.util.List;

/**
 * @author duq
 * @date 2024/1/16
 */
public interface SysConfigService {
    List<SysConfigTable> queryAll();

    void setConfig(JSONObject param);
}
