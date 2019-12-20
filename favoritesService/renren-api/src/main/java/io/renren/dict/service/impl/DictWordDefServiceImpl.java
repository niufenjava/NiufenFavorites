package io.renren.dict.service.impl;

import io.renren.common.utils.ListUtils;
import io.renren.dict.service.DictWordDefExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dict.dao.DictWordDefDao;
import io.renren.dict.entity.DictWordDefEntity;
import io.renren.dict.service.DictWordDefService;
import org.springframework.util.CollectionUtils;


@Service("dictWordDefService")
public class DictWordDefServiceImpl extends ServiceImpl<DictWordDefDao, DictWordDefEntity> implements DictWordDefService {

    @Autowired
    private DictWordDefExampleService defExampleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictWordDefEntity> page = this.page(
                new Query<DictWordDefEntity>().getPage(params),
                new QueryWrapper<DictWordDefEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void delByWordId(Long wordId){
        List<Long> ids = listIdsByWordId(wordId);
        if(CollectionUtils.isEmpty(ids)){
            return;
        }
        defExampleService.delByDefIds(ids);
        this.remove(new QueryWrapper<DictWordDefEntity>()
                .eq("word_id",wordId)
        );
    }

    @Override
    public List<DictWordDefEntity> listByWordId(Long wordId){
        return this.list(
            new QueryWrapper<DictWordDefEntity>()
                .eq("word_id",wordId)
        );
    }

    @Override
    public List<Long> listIdsByWordId(Long wordId){
        List<DictWordDefEntity> entityList = this.listByWordId(wordId);
        List<Long> ids = ListUtils.newLongList();
        if(!CollectionUtils.isEmpty(entityList)){
            for (DictWordDefEntity dictWordDefEntity : entityList) {
                ids.add(dictWordDefEntity.getId());
            }
        }
        return ids;
    }

}
