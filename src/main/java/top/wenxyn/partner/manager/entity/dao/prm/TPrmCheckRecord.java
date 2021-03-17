package top.wenxyn.partner.manager.entity.dao.prm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 0:43
 */
@Entity
@Table(name = "t_prm_check_record",
indexes = {@Index(columnList = "out_id")})
@Data
@ApiModel(value = "合作方审批信息",
        description = "企业合作管理系统中的审批记录信息，成为企业合作方需要经过内部几个角色的审核确保准确无误")
public class TPrmCheckRecord extends BaseEntity {

    @Column(name = "out_id")
    @ApiModelProperty(value = "外部关联id，用来与合作方相关信息进行一对多关联，如审核partner和contract可以共用一个字段")
    private Integer outId;

    @Column(name = "check_code")
    @ApiModelProperty(value = "审核内容码，0-partner，1-contract")
    private Integer checkCode;

    @Column(name = "check_user_name")
    @ApiModelProperty(value = "审核人名称")
    private String checkUsername;

    @Column(name = "check_uid")
    @ApiModelProperty(value = "审核人员工号")
    private Integer checkUid;

    @Column(name = "check_state")
    @ApiModelProperty(value = "审核状态，0-通过，1-拒绝")
    private Integer checkState;

    @Column(name = "reject_reason")
    @ApiModelProperty(value = "拒绝的原因")
    private String rejectReason;

    @Column(name = "check_time")
    @ApiModelProperty(value = "审核时间")
    private Date checkTime;
}
