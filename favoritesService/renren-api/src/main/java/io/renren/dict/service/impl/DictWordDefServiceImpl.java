package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictWordDefDao;
import io.renren.dict.entity.DictWordDefEntity;
import io.renren.dict.service.DictWordDefService;


@Service("dictWordDefService")
public class DictWordDefServiceImpl extends ServiceImpl<DictWordDefDao, DictWordDefEntity> implements DictWordDefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictWordDefEntity> page = this.page(
                new Query<DictWordDefEntity>().getPage(params),
                new QueryWrapper<DictWordDefEntity>()
        );

        return new PageUtils(page);
    }

}
