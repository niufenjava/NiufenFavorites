package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictWordEtymaRelDao;
import io.renren.dict.entity.DictWordEtymaRelEntity;
import io.renren.dict.service.DictWordEtymaRelService;


@Service("dictWordEtymaRelService")
public class DictWordEtymaRelServiceImpl extends ServiceImpl<DictWordEtymaRelDao, DictWordEtymaRelEntity> implements DictWordEtymaRelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictWordEtymaRelEntity> page = this.page(
                new Query<DictWordEtymaRelEntity>().getPage(params),
                new QueryWrapper<DictWordEtymaRelEntity>()
        );

        return new PageUtils(page);
    }

}
