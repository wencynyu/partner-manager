package top.wenxyn.partner.manager.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 22:12
 */
public class QueryUtil {

    public static <T, ID> Specification<T> buildBatchQuerySpecification(String field, Iterable<ID> ids){
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder)->{
            Expression<String> expression = root.get(field);
            return criteriaBuilder.in(expression.in(ids));
        };
    }
}
