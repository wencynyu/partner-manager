package top.wenxyn.partner.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import top.wenxyn.partner.manager.entity.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity, ID>
        extends
        JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>{

}
