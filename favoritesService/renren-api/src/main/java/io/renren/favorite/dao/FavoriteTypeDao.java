package io.renren.favorite.dao;

import io.renren.favorite.entity.FavoriteTypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收藏夹-类型
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-26 21:29:49
 */
@Mapper
public interface FavoriteTypeDao extends BaseMapper<FavoriteTypeEntity> {
	
}
