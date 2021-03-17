package top.wenxyn.partner.manager.entity.dao.prm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 0:42
 */
@Entity
@Table(name = "t_prm_bank",
indexes = {@Index(columnList = "partner_id")})
@Data
@ApiModel(value = "合作方银行账户信息",
        description = "企业合作管理系统中的银行账户信息")
public class TPrmBank extends BaseEntity {

    @Column(name = "partner_id")
    @ApiModelProperty(value = "用户id，用来与合作方信息进行一对一关联")
    private Integer partnerId;

    @Column(name = "name")
    @ApiModelProperty(value = "银行名称")
    private String name;

    @Column(name = "account")
    @ApiModelProperty(value = "银行账户卡号")
    private String account;

    @Column(name = "country")
    @ApiModelProperty(value = "银行所在国家")
    private String country;

    @Column(name = "province")
    @ApiModelProperty(value = "银行所在省/州")
    private String province;

    @Column(name = "city")
    @ApiModelProperty(value = "银行所在城市")
    private String city;

    @Column(name = "address")
    @ApiModelProperty(value = "银行具体位置街道")
    private String address;
}
