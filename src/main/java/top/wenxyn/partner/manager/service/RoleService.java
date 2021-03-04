package top.wenxyn.partner.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.dao.TRoleMenuRepository;
import top.wenxyn.partner.manager.dao.TRolePermissionRepository;
import top.wenxyn.partner.manager.dao.TRoleRepository;
import top.wenxyn.partner.manager.entity.TAuthRole;
import top.wenxyn.partner.manager.entity.TAuthRoleMenu;
import top.wenxyn.partner.manager.entity.TAuthRolePermission;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Service
@Transactional
public class RoleService extends AbstractService<TAuthRole, Integer, TRoleRepository> {

    @Autowired
    private TRolePermissionRepository tRolePermissionRepository;

    @Autowired
    private TRoleMenuRepository tRoleMenuRepository;


    public void insertRolePermissionRelation(List<TAuthRolePermission> tAuthRolePermissionList) {
        tRolePermissionRepository.saveAll(tAuthRolePermissionList);
    }

    public void insertRoleMenuRelation(List<TAuthRoleMenu> tAuthRoleMenuList) {
        tRoleMenuRepository.saveAll(tAuthRoleMenuList);
    }
}
