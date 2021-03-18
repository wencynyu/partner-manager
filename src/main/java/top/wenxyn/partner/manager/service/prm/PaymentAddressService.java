package top.wenxyn.partner.manager.service.prm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.repository.prm.TPartnerRepository;
import top.wenxyn.partner.manager.repository.prm.TPaymentAddressRepository;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPaymentAddress;
import top.wenxyn.partner.manager.service.AbstractService;

import javax.transaction.Transactional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 18:10
 */
@Service
@Transactional
public class PaymentAddressService extends AbstractService<TPrmPaymentAddress, Integer, TPaymentAddressRepository> {
    @Autowired
    private TPartnerRepository tPartnerRepository;

    public TPrmPaymentAddress bindPaymentAddressInfo(TPrmPaymentAddress tPrmPaymentAddress, TPrmPartner tPrmPartner) {
        TPrmPaymentAddress save = repository.save(tPrmPaymentAddress);
        tPrmPartner.setPaymentAddressId(tPrmPaymentAddress.getId());
        tPartnerRepository.save(tPrmPartner);
        return save;
    }
}
