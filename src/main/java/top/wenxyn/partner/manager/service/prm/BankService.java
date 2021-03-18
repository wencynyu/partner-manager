package top.wenxyn.partner.manager.service.prm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.repository.prm.TBankRepository;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmBank;
import top.wenxyn.partner.manager.repository.prm.TPartnerRepository;
import top.wenxyn.partner.manager.service.AbstractService;

import javax.transaction.Transactional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 18:08
 */
@Service
@Transactional
public class BankService extends AbstractService<TPrmBank, Integer, TBankRepository> {
    @Autowired
    private TPartnerRepository tPartnerRepository;

    public TPrmBank bindBankInfo(TPrmBank tPrmBank, TPrmPartner tPrmPartner) {
        TPrmBank save = repository.save(tPrmBank);
        tPrmPartner.setBankId(tPrmBank.getId());
        tPartnerRepository.save(tPrmPartner);
        return save;
    }
}
