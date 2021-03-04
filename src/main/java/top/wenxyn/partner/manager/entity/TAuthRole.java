package top.wenxyn.partner.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Entity
@Table(name = "t_auth_role")
@Data
@ApiModel(value = "角色实体",
        description = "角色实体，通过与角色（role）的多对多关系实现与用户（user）的多对多关系，该实体用来实现rbac的角色控制")
public class TAuthRole extends BaseEntity{
    @ApiModelProperty(value = "角色名称")
    @Column(name = "name", length = 63, unique = true)
    private String name;

    @ApiModelProperty(value = "权限集合")
    @Transient
    private Set<TAuthPermission> permissions;

    @ApiModelProperty(value = "菜单集合")
    @Transient
    private Set<TAuthMenu> menus;

    @ApiModelProperty(value = "角色集合")
    @Transient
    private Set<TAuthUser> users;
}
