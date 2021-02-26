package top.wenxyn.partner.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_auth_permission")
@Data
public class TAuthPermission extends BaseEntity {

    @Column(name = "name", length = 63)
    private String name;
}
