package top.wenxyn.partner.manager.dao;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.entity.TAuthUser;

@Repository
public interface TUserRepository extends BaseRepository<TAuthUser, Integer> {
}
