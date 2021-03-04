package top.wenxyn.partner.manager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 18:25
 */
@Entity
@Table(name = "t_auth_menu")
@Data
@ApiModel(value = "菜单栏实体",
        description = "菜单栏实体，通过与角色（role）的多对多关系实现与用户（user）的多对多关系，该实体用来实现动态多级菜单栏")
public class TAuthMenu extends BaseEntity {

    @ApiModelProperty(value = "目录名称")
    @Column(name = "name", length = 255, unique = true)
    private String name;

    @ApiModelProperty(value = "组件路由地址")
    @Column(name = "route", length = 255, unique = true)
    private String route;

    @ApiModelProperty(value = "层级，默认0代表最上层菜单")
    @Column(name = "level", columnDefinition = "int default 0")
    private Integer level = 0;

    @ApiModelProperty(value = "父菜单的id")
    @Column(name = "parentId", columnDefinition = "int default 0")
    private Integer parentId = 0;

    @ApiModelProperty(value = "排序字段")
    @Column(name = "sort", columnDefinition = "int default 0")
    private Integer sort = 0;

    @Transient
    private Set<TAuthRole> roles;
}
