package top.wenxyn.partner.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TAuthUser extends BaseEntity{
    @Column(name = "name", length = 63)
    private String name;

    @Column(name = "username", length = 63, unique = true)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "email", length = 255)
    private String email;

    @Transient
    private Set<String> roles;

    @Transient
    private Set<String> permissions;
}
