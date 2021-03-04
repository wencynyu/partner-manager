package top.wenxyn.partner.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
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
public class TAuthUser extends BaseEntity{
    @Column(name = "name", length = 63)
    private String name;

    @Column(name = "username", length = 63, unique = true)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "status", columnDefinition = "int default 0")
    private Integer status = 0;

    @Column(name = "login_try_times", columnDefinition = "int default 0")
    private Integer loginTryTimes = 0;

    @Transient
    private Set<TAuthRole> roles;

    @Transient
    private Set<TAuthPermission> permissions;

    @Transient
    private Set<TAuthMenu> menus;
}
