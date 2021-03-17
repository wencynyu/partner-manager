package top.wenxyn.partner.manager.service.prm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.component.EmailService;
import top.wenxyn.partner.manager.repository.prm.TPartnerRepository;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.service.AbstractService;
import top.wenxyn.partner.manager.service.auth.SystemUser;
import top.wenxyn.partner.manager.util.AuthUtil;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 18:00
 */
@Service
@Transactional
public class PartnerService extends AbstractService<TPrmPartner, Integer, TPartnerRepository> {

    @Autowired
    private EmailService emailService;

    private static final String APPLY_EMAIL_SUBJECT = "你好，有一份合作方申请请注意查收";

    public TPrmPartner bindWithUser(TPrmPartner tPrmPartner) {
        SystemUser curUser = AuthUtil.getCurUser();
        Integer userId = curUser.getId();
        tPrmPartner.setUid(userId);
        tPrmPartner.setState(Constant.PartnerState.POTENTIAL.getState());
        return repository.save(tPrmPartner);
    }

    public TPrmPartner applyToFormalPartner(TPrmPartner tPrmPartner) {
        tPrmPartner.setState(Constant.PartnerState.APPLYING.getState());
        String[] receivers = emailService.getReceivers();
        String text = "尊敬的审核员，你好：\n" +
                "\t我方企业[" + tPrmPartner + "]经过充分的考虑决定成为贵企业的正式合作方。\n" +
                "\t请贵企业收到邮件后尽快审核并回复。" +
                "\tdate:" + new Date() + '\n';
        emailService.sendMail(APPLY_EMAIL_SUBJECT, text, receivers);
        return repository.save(tPrmPartner);
    }
}
