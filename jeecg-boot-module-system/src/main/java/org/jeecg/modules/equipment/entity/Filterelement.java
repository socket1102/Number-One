package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/14 9:39
 */
@Data
@TableName("filterelement")
//value = "Filterelement",description = "滤芯表" value默认值类名，description提供详细的类描述。
@ApiModel(value = "Filterelement",description = "滤芯表")
public class Filterelement {
    /**
     * @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改
     * value–字段说明
     * name–重写属性名字
     * dataType–重写属性类型
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     */
    //type = IdType.UUID自动生成主键
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "编号")
    private String filterelementId;
    @ApiModelProperty(value = "滤芯名称")
    private String filterelementName;
    @ApiModelProperty(value = "可用天数")
    private String validity;
    @ApiModelProperty(value = "最低更换天数")
    private String replacementdays;
    @ApiModelProperty(value = "滤芯图片")
    private String images;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
