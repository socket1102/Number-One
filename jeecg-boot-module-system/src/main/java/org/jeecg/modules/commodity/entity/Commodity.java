package org.jeecg.modules.commodity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/20 20:51
 */
@Data
@TableName("commodity_type")
@ApiModel(value = "Commodity",description = "商品类型表")
public class Commodity {
    @ApiModelProperty(value = "商品类型id")
    @TableId(type = IdType.UUID)
    private String typeId;
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
