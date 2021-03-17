package top.wenxyn.partner.manager.service;

import org.springframework.data.domain.Page;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;
import top.wenxyn.partner.manager.entity.vo.PageVO;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
public interface Service<T extends BaseEntity, ID> {

    long queryCount();

    T queryOneById(ID id);

    Page<T> queryByIdsAndPage(Iterable<ID> ids, PageVO pageVO);

    Page<T> queryAllByPage(PageVO pageVO);

    T update(T t);

    T insert(T t);

    void deleteById(ID id);

    void delete(T t);

    void deleteInBatch(Iterable<T> it);
}
