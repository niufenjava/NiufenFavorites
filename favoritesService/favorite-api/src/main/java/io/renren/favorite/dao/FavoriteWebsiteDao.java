package io.renren.favorite.dao;

import io.renren.favorite.entity.FavoriteWebsiteEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收藏夹-站点
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-26 21:29:49
 */
@Mapper
public interface FavoriteWebsiteDao extends BaseMapper<FavoriteWebsiteEntity> {
	
}
