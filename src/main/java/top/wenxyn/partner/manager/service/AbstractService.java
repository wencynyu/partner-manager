package top.wenxyn.partner.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.repository.BaseRepository;
import top.wenxyn.partner.manager.entity.dao.BaseEntity;
import top.wenxyn.partner.manager.entity.vo.PageVO;
import top.wenxyn.partner.manager.util.JPAUtil;

import javax.persistence.criteria.*;
import java.util.Optional;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
public abstract class
        AbstractService<T extends BaseEntity, ID, R extends BaseRepository<T, ID>>
    implements Service<T, ID>{

    @Autowired
    protected R repository;

    @Override
    public long queryCount() {
        return repository.count();
    }

    @Override
    public T queryOneById(ID id) {
        return repository.getOne(id);
    }

    @Override
    public Page<T> queryByIdsAndPage(Iterable<ID> ids, PageVO pageVO) {
        if (pageVO == null){
            return Page.empty();
        }

        Specification<T> specification = (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            Expression<String> expression = root.get("id");
            return criteriaBuilder.in(expression.in(ids));
        };

        PageRequest pageRequest = PageRequest
                .of(pageVO.getPage() - 1,
                        Constant.DEFAULT_PAGE_SIZE,
                        Sort.by(
                                (pageVO.getAsc() != null && pageVO.getAsc()) ? Sort.Direction.ASC : Sort.Direction.DESC,
                                pageVO.getSortedFields().toArray(new String[0])));
        return repository.findAll(specification, pageRequest);
    }

    @Override
    public Page<T> queryAllByPage(PageVO pageVO) {
        if (pageVO == null){
            return Page.empty();
        }

        PageRequest pageRequest = PageRequest
                .of(pageVO.getPage() - 1,
                        Constant.DEFAULT_PAGE_SIZE,
                        Sort.by(
                                (pageVO.getAsc() != null && pageVO.getAsc()) ? Sort.Direction.ASC : Sort.Direction.DESC,
                                pageVO.getSortedFields() != null ? pageVO.getSortedFields().toArray(new String[0]) : new String[]{"id"}));
        return repository.findAll(pageRequest);
    }

    @Override
    public T insert(T t) {
        return repository.save(t);
    }

    @Override
    public T update(T t) {
        Optional<T> one = repository.findById((ID) t.getId());
        if (one.isPresent()){
            T t1 = one.get();
            JPAUtil.copyNotNullProperties(t, t1);
            repository.save(t1);
            return t1;
        }
        return null;
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteInBatch(Iterable<T> it) {
        repository.deleteInBatch(it);
    }
}
