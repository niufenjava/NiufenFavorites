package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictEtymaDao;
import io.renren.dict.entity.DictEtymaEntity;
import io.renren.dict.service.DictEtymaService;


@Service("dictEtymaService")
public class DictEtymaServiceImpl extends ServiceImpl<DictEtymaDao, DictEtymaEntity> implements DictEtymaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictEtymaEntity> page = this.page(
                new Query<DictEtymaEntity>().getPage(params),
                new QueryWrapper<DictEtymaEntity>()
        );

        return new PageUtils(page);
    }

}
