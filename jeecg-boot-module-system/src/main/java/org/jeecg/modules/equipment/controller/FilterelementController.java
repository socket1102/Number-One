package org.jeecg.modules.equipment.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.equipment.entity.Filterelement;
import org.jeecg.modules.equipment.service.IFilterelementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/14 10:27
 */
@Api(tags="滤芯表")
@RestController
@RequestMapping("/equipment/filterelement")
public class FilterelementController {
    @Autowired
    @Qualifier("IFilterelementService")
    private IFilterelementService iFilterelementService;
    //请求方式：查询用get 新增用Post 修改put 删除DELETE
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    //@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”；
    @ApiOperation(value = "滤芯表-查询",notes = "滤芯表-查询")
    public Result<IPage<Filterelement>> list(Filterelement filterelement,@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                                             @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize,
                                             HttpServletRequest request){
        Result<IPage<Filterelement>> result=new Result<IPage<Filterelement>>();
        //封装分页条件
        Page<Filterelement> page=new Page<Filterelement>(pageNo,pageSize);
        String filterelementName=filterelement.getFilterelementName();
        filterelement.setFilterelementName(null);
        //封装查询条件,如果存在值则会自动根据实体类的属性名称封装查询条件。
        QueryWrapper<Filterelement> queryWrapper= QueryGenerator.initQueryWrapper(filterelement,request.getParameterMap());
        if (filterelementName!=null){
            queryWrapper.like("filterelement_name",filterelementName);
        }
        //调用mybatis plus自带的分页条件查询的方法
        IPage<Filterelement> iPage=iFilterelementService.page(page,queryWrapper);
        result.setResult(iPage);
        return result;
    }
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ApiOperation(value = "滤芯表-删除",notes = "滤芯表-删除")
    public  Result<?> del(String id){
        Result<?> result=new Result<>();
        Boolean bo=iFilterelementService.removeById(id);
        if(bo)
            result.setMessage("删除成功");
        else
            result.setMessage("删除失败");

        result.setSuccess(bo);
        return  result;
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "滤芯表-增加",notes = "滤芯表-增加")
    public  Result<?> add(@RequestBody Filterelement filterelement){
        Result<?> result=new Result<>();
        Boolean bo=iFilterelementService.save(filterelement);
        if(bo)
            result.setMessage("增加成功");
        else
            result.setMessage("增加失败");

        result.setSuccess(bo);
        return  result;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ApiOperation(value = "滤芯表-修改",notes = "滤芯表-修改")
    public  Result<?> edit(@RequestBody Filterelement filterelement){
        Result<?> result=new Result<>();
        Boolean bo=iFilterelementService.updateById(filterelement);
        if(bo)
            result.setMessage("修改成功");
        else
            result.setMessage("修改失败");

        result.setSuccess(bo);
        return  result;
    }

}
