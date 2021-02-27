package top.wenxyn.partner.manager.dao;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.entity.TAuthUserRole;

import java.util.List;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Repository
public interface TUserRoleRepository extends BaseRepository<TAuthUserRole, Integer> {
    List<TAuthUserRole> findByUserId(Integer userId);
}
