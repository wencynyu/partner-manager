package top.wenxyn.partner.manager.entity.vo;

import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmBank;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPaymentAddress;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/4/1 1:13
 */
@Data
public class PartnerVO {
    private TPrmPartner tPrmPartner;

    private TPrmPaymentAddress tPrmPaymentAddress;

    private TPrmBank tPrmBank;
}
