package io.renren.dict.dao;

import io.renren.dict.entity.DictWordArticleRelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典-单词-文章关联表
 * 
 * @author niufen
 * @email haijun917@qq.com
 * @date 2019-12-18 22:21:34
 */
@Mapper
public interface DictWordArticleRelDao extends BaseMapper<DictWordArticleRelEntity> {
	
}
