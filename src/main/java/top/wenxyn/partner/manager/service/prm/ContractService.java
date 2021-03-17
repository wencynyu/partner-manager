package top.wenxyn.partner.manager.service.prm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.component.EmailService;
import top.wenxyn.partner.manager.repository.prm.TContractRepository;
import top.wenxyn.partner.manager.repository.prm.TPartnerRepository;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmContract;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.service.AbstractService;
import top.wenxyn.partner.manager.util.AuthUtil;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 18:11
 */
@Service
@Transactional
public class ContractService extends AbstractService<TPrmContract, Integer, TContractRepository> {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TPartnerRepository tPartnerRepository;

    private static final String APPLY_EMAIL_SUBJECT = "你好，有一份合同申请请注意查收";

    public TPrmContract createContract(TPrmContract tPrmContract, Integer partnerId) {
        tPrmContract.setState(Constant.ContractState.APPLYING.getState());
        String[] receivers = emailService.getReceivers();
        Optional<TPrmPartner> tPrmPartner = tPartnerRepository.findById(tPrmContract.getPartnerId());
        String text = "尊敬的审核员，你好：\n" +
                "\t我方企业[" + tPrmPartner + "]经过充分的考虑决定与贵企业签订一份合同：\n" +
                "\t" + tPrmContract +
                "\t请贵企业收到邮件后尽快审核并回复。" +
                "\tdate:" + new Date() + '\n';
        emailService.sendMail(APPLY_EMAIL_SUBJECT, text, receivers);
        tPrmContract.setPartnerId(partnerId);
        tPrmContract.setContractNo(AuthUtil.getUUIDNo());
        return repository.save(tPrmContract);
    }
}
