package top.wenxyn.partner.manager.entity.dao.prm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

import javax.persistence.*;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 0:42
 */
@Entity
@Table(name = "t_prm_site",
indexes = {@Index(columnList = "partner_id")})
@Data
@ApiModel(value = "合作方站点信息",
        description = "企业合作管理系统中的合作方站点信息")
public class TPrmSite extends BaseEntity {

    @Column(name = "partner_id")
    @ApiModelProperty(value = "用户id，用来与合作方信息进行一对多关联")
    private Integer partnerId;

    @Column
    @ApiModelProperty(value = "合作方站点url")
    private String siteUrl;

}
