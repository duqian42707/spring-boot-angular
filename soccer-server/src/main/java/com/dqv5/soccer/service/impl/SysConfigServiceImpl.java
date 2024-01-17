package com.dqv5.soccer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.common.ConfigValue;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.SysConfigMapper;
import com.dqv5.soccer.service.SysConfigService;
import com.dqv5.soccer.table.SysConfigTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author duq
 * @date 2024/1/16
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Resource
    private SysConfigMapper sysConfigMapper;

    @Override
    public List<SysConfigTable> queryAll() {
        return sysConfigMapper.selectList(null);
    }

    @Override
    @Transactional
    public void setConfig(JSONObject param) {
        for (String key : param.keySet()) {
            ConfigValue configValue = Arrays.stream(ConfigValue.values())
                    .filter(configEnum -> Objects.equals(key, configEnum.getConfigKey()))
                    .findFirst()
                    .orElseThrow(() -> new CommonRuntimeException("非法配置键：" + key));
            SysConfigTable sysConfigTable = sysConfigMapper.selectById(key);
            if (sysConfigTable == null) {
                sysConfigTable = new SysConfigTable();
                sysConfigTable.setConfigKey(key);
                sysConfigTable.setConfigValue(param.getString(key));
                sysConfigTable.setConfigName(configValue.getConfigName());
                sysConfigMapper.insert(sysConfigTable);
            } else {
                sysConfigTable.setConfigValue(param.getString(key));
                sysConfigTable.setConfigName(configValue.getConfigName());
                sysConfigMapper.updateById(sysConfigTable);
            }
        }
    }

}
