package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.dao.BasicUserMapper;
import com.dqv5.soccer.entity.BasicUser;
import com.dqv5.soccer.entity.BasicUserRole;
import com.dqv5.soccer.entity.SysRole;
import com.dqv5.soccer.service.BasicUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 * @date 2018/7/9
 */
@Service
public class BasicUserServiceImpl implements BasicUserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private BasicUserMapper basicUserMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Value("${sysconfig.default-password}")
    private String defaultPassword;

    @Override
    public List<BasicUser> findAll() {
        return basicUserMapper.findAllByOrderById();
    }

    @Override
    public BasicUser findOne(Integer id) {
        BasicUser basicUser = basicUserMapper.findOne(id);
        return basicUser;
    }

    @Override
    public BasicUser save(BasicUser basicUser) {
        if (basicUser.getId() == null) {
            if (StringUtils.isBlank(basicUser.getPassword())) {
                basicUser.setPassword(passwordEncoder.encode(defaultPassword));
            }
            basicUserMapper.insert(basicUser);
        } else {
            basicUserMapper.updateUserInfo(basicUser);
        }
        if (basicUser.getRoles() != null) {
            basicUserMapper.deleteUserRoles(basicUser.getId());
            for (SysRole role : basicUser.getRoles()) {
                BasicUserRole userRole = new BasicUserRole();
                userRole.setUserId(basicUser.getId());
                userRole.setRoleId(role.getId());
                basicUserMapper.insertUserRole(userRole);
            }
        }
        return basicUser;
    }


    @Override
    public void delete(Integer id) {
        BasicUser one = new BasicUser();
        basicUserMapper.delete(id);
    }


}
