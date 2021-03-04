package top.wenxyn.partner.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Entity
@Table(name = "t_auth_permission")
@Data
@ApiModel(value = "权限实体",
        description = "权限实体，通过与角色（role）的多对多关系实现与用户（user）的多对多关系，该实体用来实现rbac的权限控制")
public class TAuthPermission extends BaseEntity {

    @ApiModelProperty(value = "权限名称/url")
    @Column(name = "name", length = 63, unique = true)
    private String name;

    @ApiModelProperty(value = "角色集合")
    @Transient
    private Set<TAuthRole> roles;
}
