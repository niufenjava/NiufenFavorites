package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictWordArticleRelDao;
import io.renren.dict.entity.DictWordArticleRelEntity;
import io.renren.dict.service.DictWordArticleRelService;


@Service("dictWordArticleRelService")
public class DictWordArticleRelServiceImpl extends ServiceImpl<DictWordArticleRelDao, DictWordArticleRelEntity> implements DictWordArticleRelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictWordArticleRelEntity> page = this.page(
                new Query<DictWordArticleRelEntity>().getPage(params),
                new QueryWrapper<DictWordArticleRelEntity>()
        );

        return new PageUtils(page);
    }

}
