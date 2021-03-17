package top.wenxyn.partner.manager.entity.dao.prm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Entity
@Table(name = "t_prm_contract",
indexes = {@Index(columnList = "partner_id"), @Index(columnList = "start_time"), @Index(columnList = "end_time")})
@Data
@ApiModel(value = "合作方发起的合同信息",
        description = "企业合作管理系统中的合同信息")
public class TPrmContract extends BaseEntity {
    @Column(name = "contract_no")
    private String contractNo;

    @Column(name = "partner_id")
    @ApiModelProperty(value = "合作方Id，用来与合作方信息进行一对多关联")
    private Integer partnerId;

    @Column(name = "start_time")
    @ApiModelProperty(value = "合同开始时间")
    private Date startTime;

    @Column(name = "end_time")
    @ApiModelProperty(value = "合同结束时间")
    private Date endTime;

    @Column(name = "value", precision = 15, scale = 3)
    @ApiModelProperty(value = "合同价值")
    private BigDecimal value;

    @Column(name = "content", length = 2047)
    @ApiModelProperty(value = "合同内容")
    private String content;

    @Column(name = "state")
    @ApiModelProperty(value = "合同状态：0-合作方审核通过并发起，1-企业审核通过，2-合同执行正常，3-合同结束，-1-合同被违约")
    private Integer state = 0;
}
