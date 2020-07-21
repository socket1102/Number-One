package org.jeecg.modules.commodity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.commodity.entity.Commodity;
import org.jeecg.modules.commodity.mapper.ICommodityMapper;
import org.jeecg.modules.commodity.service.ICommodityService;
import org.springframework.stereotype.Service;

/**
 * @author 成年人的崩溃就在于那么一瞬间
 * @data 2020/7/20 21:10
 */
@Service("ICommodityService")
public class CommodityServiceImpl extends ServiceImpl<ICommodityMapper,Commodity> implements ICommodityService {

}
