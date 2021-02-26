package top.wenxyn.partner.manager.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVO {
    private Integer page;
    private Boolean asc;
    private List<String> sortedFields;
}
