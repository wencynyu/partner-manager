package top.wenxyn.partner.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import top.wenxyn.partner.manager.common.Constant;
import top.wenxyn.partner.manager.dao.BaseRepository;
import top.wenxyn.partner.manager.entity.BaseEntity;
import top.wenxyn.partner.manager.entity.vo.PageVO;

import javax.persistence.criteria.*;


public abstract class
        AbstractService<T extends BaseEntity, ID, R extends BaseRepository<T, ID>>
    implements Service<T, ID>{

    @Autowired
    protected R repository;

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
                                pageVO.getSortedFields().toArray(new String[0])));
        return repository.findAll(pageRequest);
    }

    @Override
    public T insert(T t) {
        return repository.save(t);
    }

    @Override
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteInBatch(Iterable<T> it) {
        repository.deleteInBatch(it);
    }
}
