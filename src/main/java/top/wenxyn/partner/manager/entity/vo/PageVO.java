package top.wenxyn.partner.manager.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
/**
 * @author yuwenxin980214@gmail.com
 * @date 2021/2/27 19:48
 */
@Data
@ApiModel(value = "分页实体",
        description = "用在分页查询过程，前端只需要传入以下参数即可")
public class PageVO {
    @ApiModelProperty(value = "页号")
    private Integer page;
    @ApiModelProperty(value = "排序规则，默认升序")
    private Boolean asc;
    @ApiModelProperty(value = "排序字段列表")
    private List<String> sortedFields;
}
