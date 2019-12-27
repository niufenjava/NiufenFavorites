package io.renren.favorite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.favorite.entity.FavoriteWebsiteEntity;

import java.util.Map;

/**
 * 收藏夹-站点
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-26 21:29:49
 */
public interface FavoriteWebsiteService extends IService<FavoriteWebsiteEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

