package top.wenxyn.partner.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TAuthRole extends BaseEntity{
    @Column(name = "name", length = 63, unique = true)
    private String name;

    @Transient
    private Set<String> permissions;

    @Transient
    private Set<TAuthUser> users;
}
