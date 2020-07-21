package org.jeecg.modules.commodity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.commodity.entity.Commodity;
import org.jeecg.modules.commodity.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/20 21:13
 */
@RestController
@Slf4j
@RequestMapping("/commodity/commodity_type")
@Api(tags = "商品类型表")
public class CommodityTypeController {
    @Autowired
    @Qualifier("ICommodityService")
    private ICommodityService iCommodityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "商品类型表-查询", notes = "商品类型表-查询")
    public Result<IPage<Commodity>> list(Commodity commodity, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                         HttpServletRequest request) {
        Result<IPage<Commodity>> result = new Result<IPage<Commodity>>();

        Page<Commodity> page=new  Page<Commodity>(pageNo,pageSize);
        String typeName=commodity.getTypeName();
        commodity.setTypeName(null);
        QueryWrapper<Commodity> queryWrapper= QueryGenerator.initQueryWrapper(commodity,request.getParameterMap());
        if(typeName!=null){
            queryWrapper.like("type_name",typeName);
        }
        IPage<Commodity> iPage=iCommodityService.page(page,queryWrapper);
        result.setResult(iPage);
        return result;

    }
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ApiOperation(value = "商品类型表-删除",notes = "商品类型表-删除")
    public  Result<?> del(String id){
        Result<?> result=new Result<>();
        Boolean bo=iCommodityService.removeById(id);
        if(bo)
            result.setMessage("删除成功");
        else
            result.setMessage("删除失败");

        result.setSuccess(bo);
        return  result;
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "商品类型表-增加",notes = "商品类型表-增加")
    public  Result<?> add(@RequestBody  Commodity commodity){
        Result<?> result=new Result<>();
        Boolean bo=iCommodityService.save(commodity);
        if(bo)
            result.setMessage("增加成功");
        else
            result.setMessage("增加失败");

        result.setSuccess(bo);
        return  result;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ApiOperation(value = "商品类型表-修改",notes = "商品类型表-修改")
    public  Result<?> edit(@RequestBody Commodity commodity){
        Result<?> result=new Result<>();
        Boolean bo=iCommodityService.updateById(commodity);
        if(bo)
            result.setMessage("修改成功");
        else
            result.setMessage("修改失败");

        result.setSuccess(bo);
        return  result;
    }

}
