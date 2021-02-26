package top.wenxyn.partner.manager.service;

import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.dao.TUserRepository;
import top.wenxyn.partner.manager.entity.TAuthUser;

@Service
public class UserService extends AbstractService<TAuthUser, Integer, TUserRepository> {

}
