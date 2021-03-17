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
 * @date 2021/2/27 19:48
 */
@Entity
@Table(name = "t_auth_role_permission",
indexes = {@Index(columnList = "role_id"), @Index(columnList = "permission_id")})
@Data
@NoArgsConstructor
@ApiModel(value = "角色-权限中间表实体",
        description = "实现多对多关系")
public class TAuthRolePermission extends BaseEntity {

    public TAuthRolePermission(Integer roleId, Integer permissionId){
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
    @ApiModelProperty(value = "角色id")
    @Column(name = "role_id")
    private Integer roleId;

    @ApiModelProperty(value = "权限id")
    @Column(name = "permission_id")
    private Integer permissionId;
}
