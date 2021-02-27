package top.wenxyn.partner.manager.dao;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.entity.TAuthUser;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Repository
public interface TUserRepository extends BaseRepository<TAuthUser, Integer> {
    TAuthUser findByUsername(String username);
}
