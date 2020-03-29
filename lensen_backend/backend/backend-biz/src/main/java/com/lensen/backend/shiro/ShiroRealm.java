package com.lensen.backend.shiro;

import com.lensen.backend.dal.dao.manager.ManagerDao;
import com.lensen.backend.dal.domain.auth.AdminAuthPagePO;
import com.lensen.backend.dal.domain.auth.PagePO;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.dal.domain.manager.ManagerPO;
import com.lensen.backend.service.rolerights.RoleRightsAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private RoleRightsAO roleRightsAO;

    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Set<String> permissions = new HashSet<>();

        Subject subject = SecurityUtils.getSubject();
        String managerId = (String) subject.getPrincipal();
        // 将所有的，可访问的权限放到权限信息里
        if (StringUtils.isNotEmpty(managerId)) {
            AdminAuthPagePO result = roleRightsAO.queryRoleRightsList(Long.valueOf(managerId));
            if (null != result) {
                List<PagePO> pageDetail = roleRightsAO.queryManagerPageDetail(result.getApPO().getPageId());
                for (PagePO pagepo : pageDetail) {
                    permissions.add(pagepo.getUrl());
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        LOGGER.info("用户" + userName + "进行认证-----ShiroRealm.doGetAuthenticationInfo");
        ManagerDto dto = new ManagerDto();
        dto.setUsername(userName);
        ManagerPO po = managerDao.queryUser(dto);

        if (po == null) {
            throw new UnknownAccountException("用户名不存在");
        }
        if (!password.equals(po.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误");
        }
        if (!NumberUtils.BYTE_ONE.equals(po.getStatus())) {
            throw new LockedAccountException("账号已被锁定，请联系管理员！");
        }
        return new SimpleAuthenticationInfo(po, password, getName());
    }

}