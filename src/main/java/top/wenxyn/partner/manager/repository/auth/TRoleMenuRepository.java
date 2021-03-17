package top.wenxyn.partner.manager.repository.auth;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.repository.BaseRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRoleMenu;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 23:13
 */
@Repository
public interface TRoleMenuRepository extends BaseRepository<TAuthRoleMenu, Integer> {
}
