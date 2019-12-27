package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictArticleDao;
import io.renren.dict.entity.DictArticleEntity;
import io.renren.dict.service.DictArticleService;


@Service("dictArticleService")
public class DictArticleServiceImpl extends ServiceImpl<DictArticleDao, DictArticleEntity> implements DictArticleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictArticleEntity> page = this.page(
                new Query<DictArticleEntity>().getPage(params),
                new QueryWrapper<DictArticleEntity>()
        );

        return new PageUtils(page);
    }

}
