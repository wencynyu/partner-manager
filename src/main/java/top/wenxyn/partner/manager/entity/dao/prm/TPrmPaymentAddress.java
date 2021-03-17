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
 * @date 2021/3/13 0:41
 */
@Entity
@Table(name = "t_prm_payment_address",
indexes = {@Index(columnList = "partner_id")})
@Data
@ApiModel(value = "合作方地址信息",
        description = "企业合作管理系统中的合作方地址信息")
public class TPrmPaymentAddress extends BaseEntity {
    @Column(name = "partner_id")
    @ApiModelProperty(value = "用户id，用来与合作方信息进行一对一关联")
    private Integer partnerId;

    @Column(name = "country")
    @ApiModelProperty(value = "企业方所在国家")
    private String country;

    @Column(name = "province")
    @ApiModelProperty(value = "企业方所在省/州")
    private String province;

    @Column(name = "city")
    @ApiModelProperty(value = "企业方所在城市")
    private String city;

    @Column(name = "address")
    @ApiModelProperty(value = "企业方具体位置街道")
    private String address;
}
