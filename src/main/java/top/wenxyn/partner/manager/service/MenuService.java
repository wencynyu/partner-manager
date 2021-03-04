package top.wenxyn.partner.manager.service;

import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.dao.TMenuRepository;
import top.wenxyn.partner.manager.entity.TAuthMenu;

import javax.transaction.Transactional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 23:14
 */
@Service
@Transactional
public class MenuService extends AbstractService<TAuthMenu, Integer, TMenuRepository> {
}
