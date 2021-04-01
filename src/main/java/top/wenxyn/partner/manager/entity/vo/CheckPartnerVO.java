package top.wenxyn.partner.manager.entity.vo;

import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmCheckRecord;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmPartner;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/4/1 1:15
 */
@Data
public class CheckPartnerVO {
    private TPrmCheckRecord tPrmCheckRecord;

    private TPrmPartner tPrmPartner;
}
