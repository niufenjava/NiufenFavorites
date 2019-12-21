package io.renren.notebook.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.notebook.dao.NoteContentDao;
import io.renren.notebook.entity.NoteContentEntity;
import io.renren.notebook.service.NoteContentService;


@Service("noteContentService")
public class NoteContentServiceImpl extends ServiceImpl<NoteContentDao, NoteContentEntity> implements NoteContentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NoteContentEntity> page = this.page(
                new Query<NoteContentEntity>().getPage(params),
                new QueryWrapper<NoteContentEntity>()
        );

        return new PageUtils(page);
    }

}
