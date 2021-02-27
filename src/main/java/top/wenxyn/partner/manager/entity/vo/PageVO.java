package top.wenxyn.partner.manager.entity.vo;

import lombok.Data;

import java.util.List;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Data
public class PageVO {
    private Integer page;
    private Boolean asc;
    private List<String> sortedFields;
}
