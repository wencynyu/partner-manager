package top.wenxyn.partner.manager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Entity
@Table(name = "t_auth_user_role",
indexes = {@Index(columnList = "user_id"), @Index(columnList = "role_id")})
@Data
@NoArgsConstructor
@ApiModel(value = "用户-角色中间表实体",
        description = "实现多对多关系")
public class TAuthUserRole extends BaseEntity {

    public TAuthUserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    @Column(name = "role_id")
    private Integer roleId;
}
