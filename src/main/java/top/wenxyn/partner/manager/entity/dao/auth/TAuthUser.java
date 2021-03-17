package top.wenxyn.partner.manager.entity.dao.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

import javax.persistence.*;
import java.util.Set;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Entity
@Table(name = "t_auth_user")
@Data
@ApiModel(value = "用户实体",
        description = "用户实体，通过与角色（role）的多对多关系实现与用户（user）的多对多关系，该实体用来实现rbac的用户控制")
public class TAuthUser extends BaseEntity {
    @Column(name = "name", length = 63)
    @ApiModelProperty(value = "用户真实姓名")
    private String name;

    @Column(name = "username", length = 63, unique = true)
    @ApiModelProperty(value = "用户昵称")
    private String username;

    @Column(name = "password", length = 255)
    @ApiModelProperty(value = "用户密码")
    private String password;

    @Column(name = "email", length = 255)
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @Column(name = "status", columnDefinition = "int default 0")
    @ApiModelProperty(value = "用户状态：0-正常用户，-1-禁用的用户，1-冻结的用户")
    private Integer status = 0;

    @Column(name = "locale", length = 63)
    @ApiModelProperty(value = "地区")
    private String locale = "zh-cn";

    @Column(name = "login_try_times", columnDefinition = "int default 0")
    @ApiModelProperty(value = "尝试登录的次数，超过五次会被冻结账号")
    private Integer loginTryTimes = 0;

    @Column(name = "eid", length = 15)
    @ApiModelProperty(value = "员工号")
    private String eid;

    @Transient
    private Set<TAuthRole> roles;

    @Transient
    private Set<TAuthPermission> permissions;

    @Transient
    private Set<TAuthMenu> menus;
}
