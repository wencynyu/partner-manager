package top.wenxyn.partner.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity, ID>
        extends
        JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>{

}
