package top.wenxyn.partner.manager.dao;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.entity.TAuthPermission;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Repository
public interface TPermissionRepository extends BaseRepository<TAuthPermission, Integer> {
}
