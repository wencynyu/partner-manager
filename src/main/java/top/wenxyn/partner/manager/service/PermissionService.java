package top.wenxyn.partner.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wenxyn.partner.manager.dao.TPermissionRepository;
import top.wenxyn.partner.manager.entity.BaseEntity;
import top.wenxyn.partner.manager.entity.TAuthPermission;

import java.io.Serializable;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Service
public class PermissionService extends AbstractService<TAuthPermission, Integer, TPermissionRepository> {

}
