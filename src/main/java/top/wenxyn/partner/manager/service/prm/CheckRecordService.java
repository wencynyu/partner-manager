package top.wenxyn.partner.manager.service.prm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.repository.prm.TCheckRecordRepository;
import top.wenxyn.partner.manager.repository.prm.TContractRepository;
import top.wenxyn.partner.manager.repository.prm.TPartnerRepository;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmCheckRecord;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmContract;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.service.AbstractService;
import top.wenxyn.partner.manager.service.auth.SystemUser;
import top.wenxyn.partner.manager.util.AuthUtil;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 18:11
 */
@Service
@Transactional
public class CheckRecordService extends AbstractService<TPrmCheckRecord, Integer, TCheckRecordRepository> {

    @Autowired
    private TPartnerRepository tPartnerRepository;

    @Autowired
    private TContractRepository tContractRepository;

    public TPrmCheckRecord checkPartner(TPrmCheckRecord tPrmCheckRecord, TPrmPartner tPrmPartner) {
        setChecker(tPrmCheckRecord);

        tPrmCheckRecord.setCheckCode(Constant.CheckCode.PARTNER.getState());
        tPrmPartner.setState(Constant.PartnerState.CHECKED.getState());

        tPartnerRepository.save(tPrmPartner);
        return repository.save(tPrmCheckRecord);
    }

    public TPrmCheckRecord checkContract(TPrmCheckRecord tPrmCheckRecord, TPrmContract tPrmContract) {
        setChecker(tPrmCheckRecord);

        tPrmCheckRecord.setCheckCode(Constant.CheckCode.CONTRACT.getState());
        tPrmContract.setState(Constant.ContractState.CHECKED.getState());

        tContractRepository.save(tPrmContract);
        return repository.save(tPrmCheckRecord);
    }

    private void setChecker(TPrmCheckRecord tPrmCheckRecord) {
        SystemUser curUser = AuthUtil.getCurUser();
        tPrmCheckRecord.setCheckTime(new Date());
        tPrmCheckRecord.setCheckUid(curUser.getId());
        tPrmCheckRecord.setCheckUsername(curUser.getUsername());
    }
}
