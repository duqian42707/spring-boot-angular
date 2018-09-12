package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.dao.SysDictMapper;
import com.dqv5.soccer.entity.SysDict;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.service.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 * @date 2018/9/10
 */
@Service
public class SysDictServiceImpl implements SysDictService {
    @Resource
    private SysDictMapper sysDictMapper;

    @Override
    public void save(SysDict sysDict) {
        if (sysDict.getId() == null) {
            sysDictMapper.insert(sysDict);
        } else {
            sysDictMapper.update(sysDict);
        }
    }

    @Override
    public void delete(int id) {
        sysDictMapper.delete(id);
    }

    @Override
    public void deleteByCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new CommonRuntimeException("code不能为空！");
        }
        sysDictMapper.deleteByCode(code);
    }

    @Override
    public List<SysDict> findList() {
        return sysDictMapper.findList();
    }

    @Override
    public String findDisplay(String value, String code) {
        return null;
    }
}
