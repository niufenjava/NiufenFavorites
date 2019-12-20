package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictWordDao;
import io.renren.dict.entity.DictWordEntity;
import io.renren.dict.service.DictWordService;


/**
 *
 * @author niufen
 */
@Service("dictWordService")
public class DictWordServiceImpl extends ServiceImpl<DictWordDao, DictWordEntity> implements DictWordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictWordEntity> page = this.page(
                new Query<DictWordEntity>().getPage(params),
                new QueryWrapper<DictWordEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public DictWordEntity getByName(String name){
        return this.getOne(
                new QueryWrapper<DictWordEntity>()
                        .eq("name",name)
        );
    }

}
