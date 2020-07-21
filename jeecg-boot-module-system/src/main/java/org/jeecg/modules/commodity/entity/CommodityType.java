package org.jeecg.modules.commodity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/21 16:04
 */
@Data
@TableName("commodity_type")
@ApiModel(value = "CommodityType",description = "类型滤芯表")
public class CommodityType {
    @ApiModelProperty(value = "商品类型和滤芯关系id")
    @TableId(type = IdType.UUID)
    private String relationshipId;
    @ApiModelProperty(value = "类型名称")
    private String typeName;
    @ApiModelProperty(value = "滤芯规格")
    private Integer specification;
    @ApiModelProperty(value = "创建人滤芯")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
}
