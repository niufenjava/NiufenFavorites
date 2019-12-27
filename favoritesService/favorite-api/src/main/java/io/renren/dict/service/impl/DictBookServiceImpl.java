package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictBookDao;
import io.renren.dict.entity.DictBookEntity;
import io.renren.dict.service.DictBookService;


@Service("dictBookService")
public class DictBookServiceImpl extends ServiceImpl<DictBookDao, DictBookEntity> implements DictBookService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictBookEntity> page = this.page(
                new Query<DictBookEntity>().getPage(params),
                new QueryWrapper<DictBookEntity>()
        );

        return new PageUtils(page);
    }

}
