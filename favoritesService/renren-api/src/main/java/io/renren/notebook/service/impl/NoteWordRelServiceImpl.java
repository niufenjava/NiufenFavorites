package io.renren.notebook.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.notebook.dao.NoteWordRelDao;
import io.renren.notebook.entity.NoteWordRelEntity;
import io.renren.notebook.service.NoteWordRelService;


@Service("noteWordRelService")
public class NoteWordRelServiceImpl extends ServiceImpl<NoteWordRelDao, NoteWordRelEntity> implements NoteWordRelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NoteWordRelEntity> page = this.page(
                new Query<NoteWordRelEntity>().getPage(params),
                new QueryWrapper<NoteWordRelEntity>()
        );

        return new PageUtils(page);
    }

}
