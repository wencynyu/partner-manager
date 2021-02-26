package top.wenxyn.partner.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import top.wenxyn.partner.manager.dao.BaseRepository;
import top.wenxyn.partner.manager.entity.BaseEntity;
import top.wenxyn.partner.manager.entity.vo.PageVO;

import java.io.Serializable;
import java.util.List;

public interface Service<T extends BaseEntity, ID> {

    T queryOneById(ID id);

    Page<T> queryByIdsAndPage(Iterable<ID> ids, PageVO pageVO);

    Page<T> queryAllByPage(PageVO pageVO);

    T update(T t);

    T insert(T t);

    void delete(T t);

    void deleteInBatch(Iterable<T> it);
}
