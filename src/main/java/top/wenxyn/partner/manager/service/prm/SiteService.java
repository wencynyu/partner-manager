package top.wenxyn.partner.manager.service.prm;

import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.repository.prm.TSiteRepository;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmSite;
import top.wenxyn.partner.manager.service.AbstractService;

import javax.transaction.Transactional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 18:09
 */
@Service
@Transactional
public class SiteService extends AbstractService<TPrmSite, Integer, TSiteRepository> {
}
