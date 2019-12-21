package io.renren.notebook.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.notebook.dao.NoteDao;
import io.renren.notebook.entity.NoteEntity;
import io.renren.notebook.service.NoteService;


@Service("noteService")
public class NoteServiceImpl extends ServiceImpl<NoteDao, NoteEntity> implements NoteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NoteEntity> page = this.page(
                new Query<NoteEntity>().getPage(params),
                new QueryWrapper<NoteEntity>()
        );

        return new PageUtils(page);
    }

}
