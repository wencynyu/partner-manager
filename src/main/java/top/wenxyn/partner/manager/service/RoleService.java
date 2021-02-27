package top.wenxyn.partner.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.dao.TRoleRepository;
import top.wenxyn.partner.manager.entity.TAuthRole;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Service
public class RoleService extends AbstractService<TAuthRole, Integer, TRoleRepository> {

}
