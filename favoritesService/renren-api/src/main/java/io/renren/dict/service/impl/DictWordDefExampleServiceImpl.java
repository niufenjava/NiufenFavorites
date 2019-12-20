package io.renren.dict.service.impl;

import io.renren.dict.entity.DictWordDefEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictWordDefExampleDao;
import io.renren.dict.entity.DictWordDefExampleEntity;
import io.renren.dict.service.DictWordDefExampleService;


@Service("dictWordDefExampleService")
public class DictWordDefExampleServiceImpl extends ServiceImpl<DictWordDefExampleDao, DictWordDefExampleEntity> implements DictWordDefExampleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictWordDefExampleEntity> page = this.page(
                new Query<DictWordDefExampleEntity>().getPage(params),
                new QueryWrapper<DictWordDefExampleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void delByDefIds(List<Long> defIds){
        this.remove(new QueryWrapper<DictWordDefExampleEntity>()
                .in("def_id",defIds)
        );
    }

    @Override
    public List<DictWordDefExampleEntity> listByDefId(Long defId){
        return this.list(
                new QueryWrapper<DictWordDefExampleEntity>()
                        .eq("def_id",defId)
        );
    }

}
