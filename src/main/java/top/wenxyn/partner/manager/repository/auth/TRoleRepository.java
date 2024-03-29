package top.wenxyn.partner.manager.repository.auth;


import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.repository.BaseRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthRole;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Repository
public interface TRoleRepository extends BaseRepository<TAuthRole, Integer> {
    TAuthRole findByName(String name);
}
