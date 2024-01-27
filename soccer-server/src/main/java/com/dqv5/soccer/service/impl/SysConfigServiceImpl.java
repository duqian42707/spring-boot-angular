package com.dqv5.soccer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.common.ConfigValue;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.SysConfigMapper;
import com.dqv5.soccer.pojo.SysInfo;
import com.dqv5.soccer.service.SysConfigService;
import com.dqv5.soccer.table.SysConfigTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public SysInfo getSysInfo() {
        SysInfo sysInfo = new SysInfo();
        List<SysConfigTable> list = sysConfigMapper.selectList(null);
        for (SysConfigTable sysConfigTable : list) {
            String configKey = sysConfigTable.getConfigKey();
            String configValue = sysConfigTable.getConfigValue();
            if (ConfigValue.SYS_NAME.getConfigKey().equals(configKey)) {
                sysInfo.setSysName(configValue);
            }
            if (ConfigValue.SYS_DESC.getConfigKey().equals(configKey)) {
                sysInfo.setSysDesc(configValue);
            }
            if (ConfigValue.SYS_LOGO.getConfigKey().equals(configKey)) {
                sysInfo.setSysLogo(configValue);
            }
        }
        return sysInfo;
    }


}
