package top.wenxyn.partner.manager.repository.prm;

import org.springframework.stereotype.Repository;
import top.wenxyn.partner.manager.repository.BaseRepository;
import top.wenxyn.partner.manager.entity.dao.prm.TPrmCheckRecord;

/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/3/13 18:00
 */
@Repository
public interface TCheckRecordRepository extends BaseRepository<TPrmCheckRecord, Integer> {
}
