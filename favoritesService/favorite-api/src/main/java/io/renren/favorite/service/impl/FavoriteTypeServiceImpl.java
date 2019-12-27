package io.renren.favorite.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.favorite.dao.FavoriteTypeDao;
import io.renren.favorite.entity.FavoriteTypeEntity;
import io.renren.favorite.service.FavoriteTypeService;


@Service("favoriteTypeService")
public class FavoriteTypeServiceImpl extends ServiceImpl<FavoriteTypeDao, FavoriteTypeEntity> implements FavoriteTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoriteTypeEntity> page = this.page(
                new Query<FavoriteTypeEntity>().getPage(params),
                new QueryWrapper<FavoriteTypeEntity>()
        );

        return new PageUtils(page);
    }

}
