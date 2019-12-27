package io.renren.favorite.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.favorite.dao.FavoriteWebsiteDao;
import io.renren.favorite.entity.FavoriteWebsiteEntity;
import io.renren.favorite.service.FavoriteWebsiteService;


@Service("favoriteWebsiteService")
public class FavoriteWebsiteServiceImpl extends ServiceImpl<FavoriteWebsiteDao, FavoriteWebsiteEntity> implements FavoriteWebsiteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoriteWebsiteEntity> page = this.page(
                new Query<FavoriteWebsiteEntity>().getPage(params),
                new QueryWrapper<FavoriteWebsiteEntity>()
        );

        return new PageUtils(page);
    }

}
