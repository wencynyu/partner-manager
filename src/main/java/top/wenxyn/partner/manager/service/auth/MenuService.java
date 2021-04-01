package top.wenxyn.partner.manager.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRoleMenu;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthUserRole;
import top.wenxyn.partner.manager.repository.auth.TMenuRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthMenu;
import top.wenxyn.partner.manager.repository.auth.TRoleMenuRepository;
import top.wenxyn.partner.manager.repository.auth.TUserRoleRepository;
import top.wenxyn.partner.manager.service.AbstractService;
import top.wenxyn.partner.manager.util.AuthUtil;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 23:14
 */
@Service
@Transactional
public class MenuService extends AbstractService<TAuthMenu, Integer, TMenuRepository> {

    @Autowired
    private TUserRoleRepository tUserRoleRepository;

    @Autowired
    private TRoleMenuRepository tRoleMenuRepository;

    public List<TAuthMenu> queryAllByUser() {
        Integer userId = AuthUtil.getCurUser().getId();

        Set<Integer> roleIds = tUserRoleRepository.findByUserId(userId).stream()
                .map(TAuthUserRole::getRoleId)
                .collect(Collectors.toSet());

        Set<Integer> menuIds = new HashSet<>();
        for (Integer roleId : roleIds) {
            List<TAuthRoleMenu> allByRoleId = tRoleMenuRepository.findAllByRoleId(roleId);
            menuIds.addAll(allByRoleId.stream().map(TAuthRoleMenu::getMenuId).collect(Collectors.toSet()));
        }

        return repository.findAllById(menuIds);
    }
}
