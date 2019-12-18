package io.renren.dict.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictChapterDao;
import io.renren.dict.entity.DictChapterEntity;
import io.renren.dict.service.DictChapterService;


@Service("dictChapterService")
public class DictChapterServiceImpl extends ServiceImpl<DictChapterDao, DictChapterEntity> implements DictChapterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictChapterEntity> page = this.page(
                new Query<DictChapterEntity>().getPage(params),
                new QueryWrapper<DictChapterEntity>()
        );

        return new PageUtils(page);
    }

}
