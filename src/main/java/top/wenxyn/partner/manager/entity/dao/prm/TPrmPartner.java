package top.wenxyn.partner.manager.entity.dao.prm;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

import javax.persistence.*;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Entity
@Table(name = "t_prm_partner")
@Data
@ApiModel(value = "合作方信息",
        description = "企业合作管理系统中的合作方信息")
public class TPrmPartner extends BaseEntity {

    @Column(name = "state")
    @ApiModelProperty(value = "合作方状态：0-注册绑定企业信息后的潜在合作方，1-申请合作方，2-审核通过的正式合作方，-1-禁用的合作方")
    private Integer state;

    @Column(name = "name")
    @ApiModelProperty(value = "合作方企业名称")
    private String name;

    @Column(name = "phone_country_code")
    @ApiModelProperty(value = "电话区号")
    private String phoneCountryCode;

    @Column(name = "phone_number")
    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @Column(name = "email")
    @ApiModelProperty(value = "合作方邮箱")
    private String email;

    @Column(name = "market_scope")
    @ApiModelProperty(value = "市场领域")
    private String marketScope;

    @Column(name = "bank_id")
    @ApiModelProperty(value = "银行账户id，一对一关联字段")
    private Integer bankId;

    @Column(name = "payment_address_id")
    @ApiModelProperty(value = "地址id，一对一关联字段")
    private Integer paymentAddressId;

    @Column(name = "uid")
    @ApiModelProperty(value = "注册用户Id，用户-合作方一对多关系")
    private Integer uid;

}
