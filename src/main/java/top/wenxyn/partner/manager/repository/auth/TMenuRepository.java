package top.wenxyn.partner.manager.repository.auth;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.repository.BaseRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthMenu;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 23:14
 */
@Repository
public interface TMenuRepository extends BaseRepository<TAuthMenu, Integer> {
}
