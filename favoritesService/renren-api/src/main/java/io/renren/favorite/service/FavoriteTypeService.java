package io.renren.favorite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.favorite.entity.FavoriteTypeEntity;

import java.util.Map;

/**
 * 收藏夹-类型
 *
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-26 21:29:49
 */
public interface FavoriteTypeService extends IService<FavoriteTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

