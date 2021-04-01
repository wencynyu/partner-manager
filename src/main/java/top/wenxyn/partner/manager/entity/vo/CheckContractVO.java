package top.wenxyn.partner.manager.entity.vo;

import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmCheckRecord;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmContract;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/4/1 1:16
 */
@Data
public class CheckContractVO {
    private TPrmCheckRecord tPrmCheckRecord;

    private TPrmContract tPrmContract;
}
