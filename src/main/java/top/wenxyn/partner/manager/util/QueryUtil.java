package top.wenxyn.partner.manager.util;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 22:12
 */
public class QueryUtil {

    public static <T, ID> Specification<T> buildBatchQuerySpecification(String field, Iterable<ID> ids){
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder)->{
            CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get(field));
            for (Object o :
                    ids) {
                in.value(o);
            }
            return criteriaQuery.where(in).getRestriction();
        };
    }
}
