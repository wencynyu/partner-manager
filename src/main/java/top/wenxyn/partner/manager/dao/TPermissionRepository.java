package top.wenxyn.partner.manager.dao;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.entity.TAuthPermission;


@Repository
public interface TPermissionRepository extends BaseRepository<TAuthPermission, Integer> {
}
