package top.wenxyn.partner.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "t_auth_user")
@Data
public class TAuthUser extends BaseEntity{
    @Column(name = "name", length = 63)
    private String name;
}