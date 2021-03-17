package top.wenxyn.partner.manager.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.repository.auth.TPermissionRepository;
import top.wenxyn.partner.manager.repository.auth.TRolePermissionRepository;
import top.wenxyn.partner.manager.repository.auth.TRoleRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthPermission;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRole;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRolePermission;
import top.wenxyn.partner.manager.service.AbstractService;

import javax.transaction.Transactional;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Service
@Transactional
public class PermissionService extends AbstractService<TAuthPermission, Integer, TPermissionRepository> {
    private static final String ADMIN = "admin";

    @Autowired
    private TRolePermissionRepository tRolePermissionRepository;

    @Autowired
    private TRoleRepository tRoleRepository;

    public TAuthPermission addPermission(TAuthPermission permission) {
        TAuthPermission insert = insert(permission);
        TAuthRole adminRole = tRoleRepository.findByName(ADMIN);

        TAuthRolePermission adminPermission = new TAuthRolePermission();
        adminPermission.setRoleId(adminRole.getId());
        adminPermission.setPermissionId(insert.getId());
        tRolePermissionRepository.save(adminPermission);
        return insert;
    }
}
