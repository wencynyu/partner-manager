package top.wenxyn.partner.manager.entity.dao.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/6 18:44
 */
@Entity
@Table(name = "t_auth_role_menu",
indexes = {@Index(columnList = "role_id"), @Index(columnList = "menu_id")})
@Data
@NoArgsConstructor
@ApiModel(value = "角色-目录中间表实体",
        description = "实现多对多关系")
public class TAuthRoleMenu extends BaseEntity {

    public TAuthRoleMenu(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

    @ApiModelProperty(value = "角色id")
    @Column(name = "role_id")
    private Integer roleId;

    @ApiModelProperty(value = "菜单id")
    @Column(name = "menu_id")
    private Integer menuId;

}
