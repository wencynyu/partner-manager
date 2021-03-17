package top.wenxyn.partner.manager.service.auth;

import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.repository.auth.TMenuRepository;
import top.wenxyn.partner.manager.entity.dao.auth.TAuthMenu;
import top.wenxyn.partner.manager.service.AbstractService;

import javax.transaction.Transactional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 23:14
 */
@Service
@Transactional
public class MenuService extends AbstractService<TAuthMenu, Integer, TMenuRepository> {
}
